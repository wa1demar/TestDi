package ua.waldemar.customdi.main.feature.history.domain

interface HistoryRepository {
    suspend fun getHistory(): Result<List<HistoryItem>>
}