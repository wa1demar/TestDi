package ua.waldemar.customdi.main.feature.settings.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ua.waldemar.customdi.main.feature.settings.domain.GetSettingsFlowUseCase
import ua.waldemar.customdi.main.feature.settings.domain.SettingsItem

class SettingsViewModel(useCases: GetSettingsFlowUseCase) :  ViewModel() {

    val settings = useCases()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsItem(""))

}