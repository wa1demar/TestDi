package ua.waldemar.customdi.main.shared.data

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.api.model.UserApiModel

interface UserDataSource {
    val userModelState: Flow<Result<UserApiModel>>

    val userModel: Flow<UserApiModel>

    suspend fun refreshCurrentUser()
}