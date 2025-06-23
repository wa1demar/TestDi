package ua.waldemar.customdi.data

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.domain.ExecStatus
import ua.waldemar.customdi.domain.SignInRepository

class ApiSignInRepository() : SignInRepository {

    private val _userId = MutableSharedFlow<String>()
    override val userId = _userId.asSharedFlow()

    init {
        Log.d("LogLifecycle", "ApiSignInRepository created: $this")
    }

    override suspend fun signIn(email: String, password: String): ExecStatus {
        return AccessAPI.signIn(email, password).asExecStatus()
    }

    private suspend fun Result<String>.asExecStatus(): ExecStatus = when {
        isSuccess -> {
            val userId = getOrNull()!!
            _userId.emit(userId)
            ExecStatus.Success
        }
        isFailure -> {
            val error = exceptionOrNull()!!
            when (error) {
                // check network error and TooManyRequestsError
//            is SignInNetworkError -> SignInResult.Failed.Network
//            is SignInTooManyRequestsError -> SignInTooManyRequests
                else ->  ExecStatus.Failed.General
            }
        }
        else -> throw IllegalStateException("Unknown result")
    }
}