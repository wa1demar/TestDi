package ua.waldemar.customdi.main.model.domain

import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    val userInfo: Flow<Result<UserInfoModel>>

    suspend fun refreshCurrentUser()
}