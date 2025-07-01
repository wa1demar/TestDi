package ua.waldemar.customdi.main.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.di.modules.AuthUseCaseModule
import ua.waldemar.customdi.presentation.ui.screens.signin.SignInViewModel
import ua.waldemar.customdi.presentation.ui.screens.signup.SignUpViewModel

class AppViewModelFactoryContributor(
    private val authUseCases: AuthUseCaseModule
) : ViewModelFactoryContributor {
    override fun provide(): Map<Class<out ViewModel>, () -> ViewModel> {
        return mapOf(
            SignInViewModel::class.java to { SignInViewModel(authUseCases.signInInteractor) },
            SignUpViewModel::class.java to {
                SignUpViewModel(authUseCases.signUpInteractor, authUseCases.signInInteractor)
            }
        )
    }
}