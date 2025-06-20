package ua.waldemar.customdi.main.model.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.error.ApiErrorCodes
import ua.waldemar.customdi.api.model.UserApiModel
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.model.domain.UserInfoModel

internal class ApiUserDetailsRepository(
    private val errorHandler: UnexpectedErrorHandler,
) : UserDetailsRepository {

    private val _userModelState = MutableSharedFlow<Result<UserInfoModel>>(replay = 1)
    override val userInfo: Flow<Result<UserInfoModel>> = _userModelState.asSharedFlow()

    override suspend fun refreshCurrentUser() {
        AccessAPI.getUser(listOf("firstName", "lastName", "middleName"))
            .onUnexpectedErrorNull(ApiErrorCodes.TOO_MANY_REQUESTS) { error ->
                errorHandler.handle(error)
            }?.let { result ->
                _userModelState.emit(result.toDomainUser())
            }
    }
}

private fun Result<UserApiModel>.toDomainUser(): Result<UserInfoModel> {
    val error = exceptionOrNull()
    return if (error != null) {
        Result.failure(error)
    } else {
        val user = getOrNull()
        if (user == null) {
            Result.failure(IllegalStateException("Empty User"))
        } else {
            Result.success(
                UserInfoModel(
                    user.firstName,
                    user.lastName,
                    user.middleName)
            )
        }
    }
}
