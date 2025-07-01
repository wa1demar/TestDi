package ua.waldemar.customdi.main.di.modules

import ua.waldemar.customdi.domain.SignInInteractor
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.domain.SignOutInteractor
import ua.waldemar.customdi.domain.SignUpInteractor
import ua.waldemar.customdi.domain.SignUpRepository

class AuthUseCaseModule(
    private val signInRepository: SignInRepository,
    private val signUpRepository: SignUpRepository
) {
    val signInInteractor: SignInInteractor
        get() = SignInInteractor(signInRepository)

    val signUpInteractor: SignUpInteractor
        get() = SignUpInteractor(signUpRepository)

    val signOutInteractor: SignOutInteractor
        get() = SignOutInteractor()
}