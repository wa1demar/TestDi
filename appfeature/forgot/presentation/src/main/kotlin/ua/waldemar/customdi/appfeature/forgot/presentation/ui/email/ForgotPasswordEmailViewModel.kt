package ua.waldemar.customdi.appfeature.forgot.presentation.ui.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordInteractor

class ForgotPasswordEmailViewModel(
    private val passwordInteractor: ResetPasswordInteractor,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _nextEvent = Channel<Unit>()
    val nextEvent: ReceiveChannel<Unit> = _nextEvent

    fun onEmailChanged(value: String) = viewModelScope.launch {
        _email.value = value
    }

    fun onContinueClicked() = viewModelScope.launch {
        _isLoading.value = true
        delay(1500)
        _nextEvent.send(Unit)
        _isLoading.value = false
    }
}