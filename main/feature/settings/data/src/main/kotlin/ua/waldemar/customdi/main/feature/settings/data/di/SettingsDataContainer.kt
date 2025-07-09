package ua.waldemar.customdi.main.feature.settings.data.di

import ua.waldemar.customdi.main.feature.settings.data.SettingsRepositoryImpl
import ua.waldemar.customdi.main.feature.settings.domain.SettingsRepository

class SettingsDataContainer {

    val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImpl()
    }
}