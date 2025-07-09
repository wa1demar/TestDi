package ua.waldemar.customdi.main.feature.settings.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.waldemar.customdi.main.feature.settings.domain.SettingsItem
import ua.waldemar.customdi.main.feature.settings.domain.SettingsRepository
import kotlin.random.Random

class SettingsRepositoryImpl : SettingsRepository {
    private val _settings = MutableStateFlow(SettingsItem(""))
    override val settings: StateFlow<SettingsItem> = _settings.asStateFlow()

    init {
        _settings.value = SettingsItem(Random.nextInt().toString())
    }
}