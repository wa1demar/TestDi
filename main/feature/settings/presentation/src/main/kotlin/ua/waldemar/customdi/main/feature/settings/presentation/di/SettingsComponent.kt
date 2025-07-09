package ua.waldemar.customdi.main.feature.settings.presentation.di

import ua.waldemar.customdi.core.di.FeatureComponent
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.feature.settings.data.di.SettingsDataContainer
import ua.waldemar.customdi.main.feature.settings.domain.GetSettingsFlowUseCase

class SettingsComponent : FeatureComponent() {
    // Приклад dataContainer (заміни на реальний, коли буде)
     private val dataContainer = SettingsDataContainer()

    // Приклад useCases (заміни на реальний, коли буде)
     private val useCases = GetSettingsFlowUseCase(dataContainer.settingsRepository)

    override val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
             SettingsViewModelFactoryContributor(useCases)
        )
    }
}