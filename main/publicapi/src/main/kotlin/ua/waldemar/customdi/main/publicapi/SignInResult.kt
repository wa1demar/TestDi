package ua.waldemar.customdi.main.publicapi

sealed interface SignInResult {
    data class Success(val userId: String) : SignInResult
    abstract class Failed : SignInResult {
        object General : Failed()
        object Network : Failed()
    }
}

object SignInTooManyRequests : SignInResult.Failed()

internal fun Result<String>.asSignInResult(): SignInResult = when {
    isSuccess -> {
        val value = getOrNull()!!
        when (value) {
            is String -> SignInResult.Success(value as String)
            else -> SignInResult.Failed.General
        }
    }
    isFailure -> {
        val error = exceptionOrNull()!!
        when (error) {
            // check network error and TooManyRequestsError
//            is SignInNetworkError -> SignInResult.Failed.Network
//            is SignInTooManyRequestsError -> SignInTooManyRequests
            else -> SignInResult.Failed.General
        }
    }
    else -> throw IllegalStateException("Unknown result")
}