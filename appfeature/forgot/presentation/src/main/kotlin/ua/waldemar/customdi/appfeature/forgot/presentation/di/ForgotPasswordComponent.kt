package ua.waldemar.customdi.appfeature.forgot.presentation.di

import android.content.Context
import ua.waldemar.customdi.appfeature.forgot.data.di.ForgotPasswordDataContainer
import ua.waldemar.customdi.core.di.FeatureComponent
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor

class ForgotPasswordComponent(appContext: Context) : FeatureComponent() {

    private val dataContainer = ForgotPasswordDataContainer(appContext)

    private val useCases = ForgotPasswordUseCasesModule(
        dataContainer.resetPasswordRepository
    )

    override val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            ForgotPasswordViewModelFactoryContributor(useCases.resetPasswordInteractor),
            ForgotPasswordCodeViewModelFactoryContributor()
        )
    }
}