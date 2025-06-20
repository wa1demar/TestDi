package ua.waldemar.customdi.main.model.di

import ua.waldemar.customdi.main.model.domain.ExitInteractor
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorInteractor
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo

class MainUiDomainModule internal constructor(
    private val moduleScope: ModuleScope,
    private val dataModule: UiDataModule
) {
    val exitInteractor: ExitInteractor
        get() = ExitInteractor(moduleScope)

    val unexpectedErrorInteractor: UnexpectedErrorInteractor
        get() = UnexpectedErrorInteractor(dataModule.unexpectedErrorRepository)

    val getUserInfoFlow: GetUserInfoFlow
        get() = GetUserInfoFlow(dataModule.userDetailsRepository)

    val refreshUserInfo: RefreshUserInfo
        get() = RefreshUserInfo(dataModule.userDetailsRepository)
}