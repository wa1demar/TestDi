package ua.waldemar.customdi.main.model.di.modules

import ua.waldemar.customdi.main.model.data.ApiUnexpectedErrorRepository
import ua.waldemar.customdi.main.model.data.ApiUserDetailsRepository
import ua.waldemar.customdi.main.shared.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.shared.data.DataSourceModule

class DataModule(
    dataSourceModule: DataSourceModule,
) {

    val unexpectedErrorRepository: UnexpectedErrorRepository by lazy {
        ApiUnexpectedErrorRepository(dataSourceModule.errorHandler)
    }

    val userRepository: UserDetailsRepository by lazy {
        ApiUserDetailsRepository(dataSourceModule.userDataSource)
    }
}