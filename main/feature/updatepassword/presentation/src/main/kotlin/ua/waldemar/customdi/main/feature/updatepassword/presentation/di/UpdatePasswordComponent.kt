package ua.waldemar.customdi.main.feature.updatepassword.presentation.di

import ua.waldemar.customdi.core.di.FeatureComponent
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.feature.HasViewModelCreators
import ua.waldemar.customdi.main.feature.updatepassword.data.di.UpdatePasswordDataContainer
import ua.waldemar.customdi.main.feature.updatepassword.domain.UpdatePasswordUseCase

class UpdatePasswordComponent : FeatureComponent(), HasViewModelCreators {

    private val dataContainer = UpdatePasswordDataContainer()

    private val useCase = UpdatePasswordUseCase(dataContainer.updatePasswordRepository)

    override val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            UpdatePasswordFormViewModelFactoryContributor(useCase),
            UpdatePasswordResultViewModelFactoryContributor()
        )
    }
} 