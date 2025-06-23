package ua.waldemar.customdi.di.v2

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.di.init.InitUiInteractor
import ua.waldemar.customdi.domain.SignInInteractor
import ua.waldemar.customdi.domain.SignOutInteractor
import ua.waldemar.customdi.domain.SignUpInteractor

val DomainModule: Scope.Builder.() -> Unit = {
    factory { InitUiInteractor(get()) }
    factory { SignInInteractor(get()) }
    factory { SignUpInteractor(get()) }
    factory { SignOutInteractor() }
}