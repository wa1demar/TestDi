package ua.waldemar.customdi.di.v2

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.data.ApiSignInRepository
import ua.waldemar.customdi.data.ApiSignUpRepository
import ua.waldemar.customdi.data.firebase.InitUiRepositoryImpl
import ua.waldemar.customdi.di.init.InitUiRepository
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.domain.SignUpRepository

val DataModule: Scope.Builder.() -> Unit = {
    single<SignInRepository> { ApiSignInRepository() }
    single<SignUpRepository> { ApiSignUpRepository() }
    single<InitUiRepository> {
        InitUiRepositoryImpl(
            get(),
            get(AppQualifiers.Dispatcher.IO)
        )
    }
}