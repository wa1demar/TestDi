package ua.waldemar.customdi.presentation.ui.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.domain.SignInInteractor

class SignInViewModel(
    private val signInInteractor: SignInInteractor,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _onForgotEvent = Channel<Unit>()
    val onForgotEvent = _onForgotEvent.receiveAsFlow()

    val launchUiEvent: Flow<String> = signInInteractor.userId
        .filter { userId -> userId.isNotBlank() }

    private val _signInProcess = MutableStateFlow<ExecStatus>(ExecStatus.Idle)
    val signInUIStatus: Flow<ExecStatus> = _signInProcess.filter { status ->
        status != ExecStatus.Success
    }.onEach { status ->
        if (status is ExecStatus.Failed) _password.value = ""
    }

    fun onEmailChanges(value: String) = viewModelScope.launch {
        _email.emit(value)
    }

    fun onPasswordChanges(value: String) = viewModelScope.launch {
        _password.emit(value)
    }

    fun onLoginClicked() = viewModelScope.launch {
        _signInProcess.value = ExecStatus.InProgress
        combine(email, password) { email, password ->
            signInInteractor.signIn(email, password)
                .takeUnless { status -> status == ExecStatus.Success }
                ?.let { process ->
                    _signInProcess.value = process
                }
        }.take(1).launchIn(viewModelScope)
    }

    fun onForgotPasswordClicked() = viewModelScope.launch {
        _onForgotEvent.send(Unit)
    }
}