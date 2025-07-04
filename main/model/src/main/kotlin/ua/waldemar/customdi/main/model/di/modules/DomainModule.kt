package ua.waldemar.customdi.main.model.di.modules

import ua.waldemar.customdi.main.model.domain.UnexpectedErrorInteractor
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo

class DomainModule(
    private val unexpectedErrorRepository: UnexpectedErrorRepository,
    private val userDetailsRepository: UserDetailsRepository
) {

    val unexpectedErrorInteractor: UnexpectedErrorInteractor
        get() = UnexpectedErrorInteractor(unexpectedErrorRepository)

    val getUserInfoFlow: GetUserInfoFlow
        get() = GetUserInfoFlow(userDetailsRepository)

    val refreshUserInfo: RefreshUserInfo
        get() = RefreshUserInfo(userDetailsRepository)
}