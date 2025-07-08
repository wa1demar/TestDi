package ua.waldemar.customdi.main.feature.settings.presentation.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.main.feature.settings.presentation.ui.SettingsViewModel

class SettingsViewModelFactoryContributor : ViewModelFactoryContributor {
    override fun provide() = viewModelFactories {
        factory<SettingsViewModel> { SettingsViewModel() }
    }
}