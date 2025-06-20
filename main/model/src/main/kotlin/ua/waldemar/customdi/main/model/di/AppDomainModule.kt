package ua.waldemar.customdi.main.model.di

import ua.waldemar.customdi.main.model.domain.SignInInteractor
import ua.waldemar.customdi.main.model.domain.SignUpInteractor

class AppDomainModule internal constructor(internal val appDataModule: AppDataModule) {
    val signInInteractor: SignInInteractor
        get() = SignInInteractor(appDataModule.signInRepository)

    val signUpInteractor: SignUpInteractor
        get() = SignUpInteractor(appDataModule.signUpRepository)
}