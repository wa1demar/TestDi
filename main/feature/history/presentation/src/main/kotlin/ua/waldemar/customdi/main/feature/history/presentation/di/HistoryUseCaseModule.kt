package ua.waldemar.customdi.main.feature.history.presentation.di

import ua.waldemar.customdi.main.feature.history.domain.GetHistoryUseCase
import ua.waldemar.customdi.main.feature.history.domain.HistoryRepository

class HistoryUseCaseModule(private val historyRepository: HistoryRepository) {
    val getHistoryUseCase: GetHistoryUseCase get() = GetHistoryUseCase(historyRepository)
}