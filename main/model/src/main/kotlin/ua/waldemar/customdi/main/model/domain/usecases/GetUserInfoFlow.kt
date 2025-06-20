package ua.waldemar.customdi.main.model.domain.usecases

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.model.domain.UserInfoModel

class GetUserInfoFlow(
    private val userInfoRepository: UserDetailsRepository
) {
    operator fun invoke(): Flow<Result<UserInfoModel>> {
        return userInfoRepository.userInfo
    }
}