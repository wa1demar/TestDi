package ua.waldemar.customdi.data

import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.domain.SignUpRepository
import ua.waldemar.customdi.main.view.AccessUI
import ua.waldemar.customdi.main.view.helpers.SignUpResult
import ua.waldemar.customdi.main.view.helpers.SignUpTooManyRequests

class ApiSignUpRepository : SignUpRepository {

//    private val signUpHelper by lazy {
//        AccessUI.signUpHelper
//    }

    override suspend fun signUp(email: String, password: String): ExecStatus {
//        return signUpHelper.signUp(email, password).asExecStatus()
        return ExecStatus.Success
    }

    private fun SignUpResult.asExecStatus() = when (this) {
        is SignUpResult.Success -> ExecStatus.Success
        is SignUpResult.Failed.Network -> ExecStatus.Failed.Network
        is SignUpTooManyRequests -> ExecStatus.Failed.TooManyRequests
        else -> ExecStatus.Failed.General
    }
}