package ua.waldemar.customdi.appfeature.forgot.presentation.di

import ua.waldemar.customdi.appfeature.forgot.presentation.ui.code.ForgotPasswordCodeViewModel
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.email.ForgotPasswordEmailViewModel
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordInteractor
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories

class ForgotPasswordViewModelFactoryContributor(
    private val interactor: ResetPasswordInteractor
) : ViewModelFactoryContributor {
    override fun contribute() = viewModelFactories {
        factory<ForgotPasswordEmailViewModel> { ForgotPasswordEmailViewModel(interactor) }
        factory<ForgotPasswordCodeViewModel> { ForgotPasswordCodeViewModel() }
    }
}