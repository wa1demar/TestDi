package ua.waldemar.customdi.main.feature.history.data.api

import ua.waldemar.customdi.api.model.HistoryModel

interface ApiDataSource {
    suspend fun getHistory(): Result<List<HistoryModel>>
}