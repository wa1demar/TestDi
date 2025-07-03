package ua.waldemar.customdi.main.model.di_v2.modules

import ua.waldemar.customdi.main.model.data.ApiUnexpectedErrorRepository
import ua.waldemar.customdi.main.model.data.ApiUserDetailsRepository
import ua.waldemar.customdi.main.model.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

class DataModule(dataSourceModule: DataSourceModule) {

    private val errorHandler: UnexpectedErrorHandler by lazy {
        UnexpectedErrorHandler()
    }

    val unexpectedErrorRepository: UnexpectedErrorRepository by lazy {
        ApiUnexpectedErrorRepository(errorHandler)
    }

    val userRepository: UserDetailsRepository by lazy {
        ApiUserDetailsRepository(dataSourceModule.apiDataSource, errorHandler)
    }
}