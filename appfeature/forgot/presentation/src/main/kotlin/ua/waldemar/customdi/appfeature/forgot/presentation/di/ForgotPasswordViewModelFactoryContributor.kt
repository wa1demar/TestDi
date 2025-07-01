package ua.waldemar.customdi.appfeature.forgot.presentation.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.code.ForgotPasswordCodeViewModel
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.email.ForgotPasswordEmailViewModel
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordInteractor
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor

class ForgotPasswordViewModelFactoryContributor(
    private val interactor: ResetPasswordInteractor
) : ViewModelFactoryContributor {
    override fun provide(): Map<Class<out ViewModel>, () -> ViewModel> {
        return mapOf(
            ForgotPasswordEmailViewModel::class.java to { ForgotPasswordEmailViewModel(interactor) },
            ForgotPasswordCodeViewModel::class.java to { ForgotPasswordCodeViewModel() }
        )
    }
}