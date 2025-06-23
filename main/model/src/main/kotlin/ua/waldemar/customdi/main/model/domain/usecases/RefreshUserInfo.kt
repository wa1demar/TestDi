package ua.waldemar.customdi.main.model.domain.usecases

import android.util.Log
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

class RefreshUserInfo(
    private val userInfoRepository: UserDetailsRepository
) {
    init {
        Log.d("LogLifecycle", "RefreshUserInfo created: $this")
        Log.d("LogLifecycle", "RefreshUserInfo@userInfoRepository: $userInfoRepository")
    }
    suspend operator fun invoke() {
        userInfoRepository.refreshCurrentUser()
    }
}