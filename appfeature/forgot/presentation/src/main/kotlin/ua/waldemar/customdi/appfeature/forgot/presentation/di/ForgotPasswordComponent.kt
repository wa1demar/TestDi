package ua.waldemar.customdi.appfeature.forgot.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.appfeature.forgot.data.di.ForgotPasswordDataContainer
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor

class ForgotPasswordComponent(appContext: Context) {

    private val dataContainer = ForgotPasswordDataContainer(appContext)

    private val useCases = ForgotPasswordUseCasesModule(
        dataContainer.resetPasswordRepository
    )

    private val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            ForgotPasswordViewModelFactoryContributor(useCases.resetPasswordInteractor)
        )
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        contributors.flatMap { it.provide().entries }.associate { it.toPair() }
    }
}