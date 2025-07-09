package ua.waldemar.customdi.main.feature.settings.domain

import kotlinx.coroutines.flow.Flow

class GetSettingsFlowUseCase(
    val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<SettingsItem> {
        return settingsRepository.settings
    }
}