package ua.waldemar.customdi.main.model.domain.usecases

import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

class RefreshUserInfo(
    private val userInfoRepository: UserDetailsRepository
) {
    suspend operator fun invoke() {
        userInfoRepository.refreshCurrentUser()
    }
}