package ua.waldemar.customdi.presentation.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.take
import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.domain.SignInInteractor
import ua.waldemar.customdi.domain.SignUpInteractor
import ua.waldemar.customdi.main.di.AppContainer
import ua.waldemar.customdi.presentation.ui.common.UserId

class SignUpViewModel(
    private val signUpInteractor: SignUpInteractor,
    private val signInInteractor: SignInInteractor,
) : ViewModel() {

    private val _signUpStatus = MutableStateFlow<ExecStatus>(ExecStatus.Idle)
    val signUpStatus: Flow<ExecStatus> = _signUpStatus.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    val launchUiEvent: Flow<UserId> = signInInteractor.userId.filter { it.isNotBlank() }

    fun onSignUpClicked() {
        _signUpStatus.value = ExecStatus.InProgress
        combine(email, password) { email, password ->
            when (val status = signUpInteractor.signUp(email, password)) {
                ExecStatus.Success -> signInInteractor.signIn(email, password)
                    .takeUnless { signInStatus -> signInStatus == ExecStatus.Success }

                else -> status
            }?.let { signUpStatus ->
                _signUpStatus.value = signUpStatus
            }
        }.take(1).launchIn(viewModelScope)
    }

    fun afterPasswordChanged(text: CharSequence?) {
        _password.value = text?.toString() ?: ""
    }

    fun afterEmailChanged(text: CharSequence?) {
        _email.value = text?.toString() ?: ""
    }
}