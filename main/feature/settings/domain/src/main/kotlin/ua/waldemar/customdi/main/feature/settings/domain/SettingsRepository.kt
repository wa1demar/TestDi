package ua.waldemar.customdi.main.feature.settings.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<SettingsItem>
}