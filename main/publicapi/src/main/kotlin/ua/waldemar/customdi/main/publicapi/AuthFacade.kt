package ua.waldemar.customdi.main.publicapi

import ua.waldemar.customdi.main.publicapi.di.MainPublicComponent

object AuthFacade {
    private val useCases get() = MainPublicComponent.Companion.get().useCaseModule

    suspend fun signIn(email: String, password: String): SignInResult {
        return useCases.signInInteractor.signIn(email, password)
            .asSignInResult()
    }

    suspend fun signUp(email: String, password: String): SignUpResult {
        return useCases.signUpInteractor.signUp(email, password)
            .asSignUpResult()
    }
}