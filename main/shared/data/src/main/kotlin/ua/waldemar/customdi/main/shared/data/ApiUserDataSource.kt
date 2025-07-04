package ua.waldemar.customdi.main.shared.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.mapNotNull
import ua.waldemar.customdi.api.error.ApiErrorCodes
import ua.waldemar.customdi.api.model.UserApiModel

internal class ApiUserDataSource(
    private val apiDataSource: ApiDataSource,
    private val errorHandler: UnexpectedErrorHandler
) : UserDataSource {

    private val _userModelState = MutableSharedFlow<Result<UserApiModel>>(replay = 1)
    override val userModelState: SharedFlow<Result<UserApiModel>> = _userModelState.asSharedFlow()

    override val userModel: Flow<UserApiModel> = userModelState.mapNotNull { state -> state.getOrNull() }

    override suspend fun refreshCurrentUser() {
        apiDataSource.getUser(listOf("firstName", "lastName", "middleName"))
            .onUnexpectedErrorNull(ApiErrorCodes.TOO_MANY_REQUESTS) { error ->
                errorHandler.handle(error)
            }?.let { result ->
                _userModelState.emit(result)
            }
    }
}