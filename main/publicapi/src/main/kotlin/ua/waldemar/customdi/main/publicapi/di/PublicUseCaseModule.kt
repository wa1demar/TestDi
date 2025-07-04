package ua.waldemar.customdi.main.publicapi.di

import ua.waldemar.customdi.main.publicapi.domain.signin.SignInInteractor
import ua.waldemar.customdi.main.publicapi.domain.signin.SignInRepository
import ua.waldemar.customdi.main.publicapi.domain.signup.SignUpInteractor
import ua.waldemar.customdi.main.publicapi.domain.signup.SignUpRepository

class PublicUseCaseModule(
    private val signInRepository: SignInRepository,
    private val signUpRepository: SignUpRepository,
) {
    val signInInteractor: SignInInteractor
        get() = SignInInteractor(signInRepository)

    val signUpInteractor: SignUpInteractor
        get() = SignUpInteractor(signUpRepository)
}