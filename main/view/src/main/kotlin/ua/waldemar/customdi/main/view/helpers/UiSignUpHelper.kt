package ua.waldemar.customdi.main.view.helpers

import ua.waldemar.customdi.main.model.domain.SignUpInteractor

sealed interface SignUpResult {
    object Success : SignUpResult
    abstract class Failed : SignUpResult {
        object General : Failed()
        object Network : Failed()
    }
}

object SignUpTooManyRequests : SignUpResult.Failed()

class UiSignUpHelper internal constructor(
    private val signUpInteractor: SignUpInteractor
) {
    suspend fun signUp(email: String, password: String): SignUpResult =
        signUpInteractor.signUp(email, password).asSignUpResult()

    private fun Result<Any>.asSignUpResult(): SignUpResult = when {
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
}