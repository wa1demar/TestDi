package ua.waldemar.customdi.main.model.di.v2

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorInteractor
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo

val MainDomainModule: Scope.Builder.() -> Unit = {
    MainDataModule()

    factory { UnexpectedErrorInteractor(get()) }
    factory { GetUserInfoFlow(get()) }
    factory { RefreshUserInfo(get()) }
}