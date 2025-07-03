package ua.waldemar.customdi.main.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.main.model.domain.UnexpectedError
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorInteractor
import ua.waldemar.customdi.main.view.launch.ResultUiEvent

internal sealed class MainDirection {
    data object NetworkError : MainDirection()
    data object UserStatusIssue : MainDirection()
    data class Support(val reloginAfterComplete: Boolean = false) : MainDirection()
    data object Verification : MainDirection()
    data class MigrationApproval(val migrationRequestId: Long, val employerName: String) : MainDirection()
    data object Legal : MainDirection()
    data class FinancierRedirect(
        val authorizationUrl: String? = null,
        val redirectUrl: String? = null
    ) : MainDirection()
    data object PiiUpdate : MainDirection()
    data object UpdatePassword : MainDirection()
    data object Splash : MainDirection()
    data object Home : MainDirection()
    data object Withdraw: MainDirection()
    data object History: MainDirection()
    data object Notifications: MainDirection()
}

internal class MainViewModel(
    private val unexpectedErrorInteractor: UnexpectedErrorInteractor,
) : ViewModel() {

    private val _resultUiEvent = Channel<ResultUiEvent>()
    val resultUiEvent: ReceiveChannel<ResultUiEvent> = _resultUiEvent

    private val _mainDirection = Channel<MainDirection>()
    val mainDirection: Flow<MainDirection> = _mainDirection.receiveAsFlow()

    init {
        observeUnexpectedError()
    }

    private fun observeUnexpectedError() {
        unexpectedErrorInteractor.unexpectedError.onEach { error ->
            when (error) {
                UnexpectedError.AuthFailure -> {
                    // clear session
                    _resultUiEvent.send(ResultUiEvent.ERROR)
                }
                UnexpectedError.BankNotApproved -> {
                    _mainDirection.send(MainDirection.FinancierRedirect())
                }
                UnexpectedError.EmailNotVerified -> {
                    _mainDirection.send(MainDirection.Verification)
                }
                UnexpectedError.InvalidPKToken -> {
                    invalidTokenError()
                }
                UnexpectedError.TooManyRequests -> {
                    onTooManyRequestsError()
                }
                UnexpectedError.UserNotConsented -> {
                    // clear session
                    _mainDirection.send(MainDirection.Legal)
                }
                UnexpectedError.UserStatusIssue -> {
                    // clear session
                    _mainDirection.send(MainDirection.UserStatusIssue)
                }
                else -> {
                    _mainDirection.send(MainDirection.NetworkError)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun invalidTokenError() {
        viewModelScope.launch {
            // show dialog
        }
    }

    fun onTooManyRequestsError() {
        viewModelScope.launch {
            // show dialog
        }
    }
}