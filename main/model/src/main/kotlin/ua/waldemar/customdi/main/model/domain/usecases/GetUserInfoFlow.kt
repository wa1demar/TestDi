package ua.waldemar.customdi.main.model.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.model.domain.UserInfoModel

class GetUserInfoFlow(
    private val userInfoRepository: UserDetailsRepository
) {
    init {
        Log.d("LogLifecycle", "GetUserInfoFlow created: $this")
        Log.d("LogLifecycle", "GetUserInfoFlow@userInfoRepository: $userInfoRepository")
    }
    operator fun invoke(): Flow<Result<UserInfoModel>> {
        return userInfoRepository.userInfo
    }
}