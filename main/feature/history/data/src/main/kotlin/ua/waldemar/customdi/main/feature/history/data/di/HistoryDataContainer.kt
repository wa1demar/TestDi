package ua.waldemar.customdi.main.feature.history.data.di

import ua.waldemar.customdi.main.feature.history.data.HistoryRepositoryImpl
import ua.waldemar.customdi.main.feature.history.data.api.ApiDataSource
import ua.waldemar.customdi.main.feature.history.data.api.ApiDataSourceImpl
import ua.waldemar.customdi.main.feature.history.domain.HistoryRepository

class HistoryDataContainer {
    private val apiDataSource: ApiDataSource by lazy {
        ApiDataSourceImpl()
    }

    val historyRepository: HistoryRepository by lazy {
        HistoryRepositoryImpl(apiDataSource)
    }
}