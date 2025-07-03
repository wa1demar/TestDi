package ua.waldemar.customdi.main.model.di_v2.modules

import ua.waldemar.customdi.main.model.data.api.ApiDataSource
import ua.waldemar.customdi.main.model.data.api.ApiDataSourceImpl

class DataSourceModule {
    val apiDataSource: ApiDataSource by lazy {
        ApiDataSourceImpl()
    }
}