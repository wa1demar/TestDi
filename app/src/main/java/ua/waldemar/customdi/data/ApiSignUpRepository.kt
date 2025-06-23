package ua.waldemar.customdi.data

import android.util.Log
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.domain.ExecStatus
import ua.waldemar.customdi.domain.SignUpRepository

class ApiSignUpRepository : SignUpRepository {

    init {
        Log.d("LogLifecycle", "ApiSignUpRepository created: $this")
    }

    override suspend fun signUp(email: String, password: String): ExecStatus {
        return AccessAPI.signUp(email, password).asExecStatus()
    }

    private fun Result<Any>.asExecStatus(): ExecStatus = when {
        isSuccess -> {
            val value = getOrNull()!!
            when (value) {
                is String -> ExecStatus.Success
                else -> ExecStatus.Failed.General
            }
        }
        isFailure -> {
            val error = exceptionOrNull()!!
            when (error) {
                // check network error and TooManyRequestsError
//            is SignInNetworkError -> ExecStatus.Failed.Network
//            is SignInTooManyRequestsError -> ExecStatus.Failed.TooManyRequests
                else -> ExecStatus.Failed.General
            }
        }
        else -> throw IllegalStateException("Unknown result")
    }
}