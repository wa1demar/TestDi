package ua.waldemar.customdi.data

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.main.view.AccessUI
import ua.waldemar.customdi.main.view.helpers.SignInResult
import ua.waldemar.customdi.main.view.helpers.SignInTooManyRequests

class ApiSignInRepository() : SignInRepository {

    private val signInHelper by lazy {
        AccessUI.signInHelper
    }

    private val _userId = MutableSharedFlow<String>()
    override val userId = _userId.asSharedFlow()

    override suspend fun signIn(email: String, password: String): ExecStatus {
        return signInHelper.signIn(email, password).asExecStatus()
    }

    private suspend fun SignInResult.asExecStatus() = when (this) {
        is SignInResult.Success -> {
            _userId.emit(userId)
            ExecStatus.Success
        }
        is SignInResult.Failed.Network -> ExecStatus.Failed.Network
        is SignInTooManyRequests -> ExecStatus.Failed.TooManyRequests
        else -> ExecStatus.Failed.General
    }
}