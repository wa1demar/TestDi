package ua.waldemar.customdi.main.feature.history.domain

class GetHistoryUseCase(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(): Result<List<HistoryItem>> {
        return historyRepository.getHistory()
    }
}