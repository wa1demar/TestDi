package ua.waldemar.customdi.main.feature.history.data.api

import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.model.HistoryModel

class ApiDataSourceImpl : ApiDataSource {
    override suspend fun getHistory(): Result<List<HistoryModel>> {
        return AccessAPI.getHistory()
    }
}