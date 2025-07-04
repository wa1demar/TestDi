package ua.waldemar.customdi.main.publicapi

sealed interface SignUpResult {
    object Success : SignUpResult
    abstract class Failed : SignUpResult {
        object General : Failed()
        object Network : Failed()
    }
}

object SignUpTooManyRequests : SignUpResult.Failed()

internal fun Result<Any>.asSignUpResult(): SignUpResult = when {
    isSuccess -> {
        val value = getOrNull()!!
        when (value) {
            is String -> SignUpResult.Success
            else -> SignUpResult.Failed.General
        }
    }

    isFailure -> {
        val error = exceptionOrNull()!!
        when (error) {
            // check network error and TooManyRequestsError
//            is SignInNetworkError -> SignInResult.Failed.Network
//            is SignInTooManyRequestsError -> SignInTooManyRequests
            else -> SignUpResult.Failed.General
        }
    }

    else -> throw IllegalStateException("Unknown result")
}