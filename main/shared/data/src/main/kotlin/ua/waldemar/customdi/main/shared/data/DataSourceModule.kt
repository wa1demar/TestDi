package ua.waldemar.customdi.main.shared.data

class DataSourceModule {

    private val apiDataSource: ApiDataSource by lazy {
        ApiDataSourceImpl()
    }

    val errorHandler: UnexpectedErrorHandler by lazy {
        UnexpectedErrorHandler()
    }

    val userDataSource: UserDataSource by lazy {
        ApiUserDataSource(apiDataSource, errorHandler)
    }
}