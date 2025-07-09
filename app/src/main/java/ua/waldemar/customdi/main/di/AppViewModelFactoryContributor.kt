package ua.waldemar.customdi.main.di

import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.main.di.modules.AuthUseCaseModule
import ua.waldemar.customdi.presentation.ui.screens.signin.SignInViewModel
import ua.waldemar.customdi.presentation.ui.screens.signup.SignUpViewModel

class AppViewModelFactoryContributor(
    private val authUseCases: AuthUseCaseModule
) : ViewModelFactoryContributor {
    override fun contribute() = viewModelFactories {
        factory<SignInViewModel> { SignInViewModel(authUseCases.signInInteractor) }
        factory<SignUpViewModel> { SignUpViewModel(authUseCases.signUpInteractor, authUseCases.signInInteractor) }
    }
}