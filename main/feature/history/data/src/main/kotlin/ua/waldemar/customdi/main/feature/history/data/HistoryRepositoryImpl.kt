package ua.waldemar.customdi.main.feature.history.data

import ua.waldemar.customdi.main.feature.history.data.api.ApiDataSource
import ua.waldemar.customdi.main.feature.history.domain.HistoryItem
import ua.waldemar.customdi.main.feature.history.domain.HistoryRepository

class HistoryRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : HistoryRepository {
    override suspend fun getHistory(): Result<List<HistoryItem>> {
        return apiDataSource.getHistory()
            .map {
                it.map { HistoryItem(it.id, it.title) }
            }
    }
}