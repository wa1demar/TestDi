package ua.waldemar.customdi.data

import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.domain.SignUpRepository
import ua.waldemar.customdi.main.publicapi.AuthFacade
import ua.waldemar.customdi.main.publicapi.SignUpResult
import ua.waldemar.customdi.main.publicapi.SignUpTooManyRequests

class ApiSignUpRepository : SignUpRepository {

    override suspend fun signUp(email: String, password: String): ExecStatus {
        return AuthFacade.signUp(email, password).asExecStatus()
    }

    private fun SignUpResult.asExecStatus() = when (this) {
        is SignUpResult.Success -> ExecStatus.Success
        is SignUpResult.Failed.Network -> ExecStatus.Failed.Network
        is SignUpTooManyRequests -> ExecStatus.Failed.TooManyRequests
        else -> ExecStatus.Failed.General
    }
}