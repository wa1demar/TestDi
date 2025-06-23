package ua.waldemar.customdi.di.v2

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.presentation.AppViewModel
import ua.waldemar.customdi.presentation.ui.application.screens.signin.SignInViewModel
import ua.waldemar.customdi.presentation.ui.application.screens.signup.SignUpViewModel

val ActivityModule: Scope.Builder.() -> Unit = {
    factory { AppViewModel(get()) }
    factory { SignInViewModel(get()) }
    factory { SignUpViewModel(get(), get()) }
}