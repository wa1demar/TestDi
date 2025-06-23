package ua.waldemar.customdi.main.model.di.v2

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.main.model.data.ApiUnexpectedErrorRepository
import ua.waldemar.customdi.main.model.data.ApiUserDetailsRepository
import ua.waldemar.customdi.main.model.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

val MainDataModule: Scope.Builder.() -> Unit = {
    single { UnexpectedErrorHandler() }
    single<UserDetailsRepository> { ApiUserDetailsRepository(get()) }
    single<UnexpectedErrorRepository> { ApiUnexpectedErrorRepository(get()) }
}