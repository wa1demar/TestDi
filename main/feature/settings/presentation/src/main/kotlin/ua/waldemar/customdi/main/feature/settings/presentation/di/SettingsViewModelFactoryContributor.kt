package ua.waldemar.customdi.main.feature.settings.presentation.di

import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.main.feature.settings.domain.GetSettingsFlowUseCase
import ua.waldemar.customdi.main.feature.settings.presentation.ui.SettingsViewModel

class SettingsViewModelFactoryContributor(val useCases: GetSettingsFlowUseCase) : ViewModelFactoryContributor {
    override fun contribute() = viewModelFactories {
        factory<SettingsViewModel> { SettingsViewModel(useCases) }
    }
}