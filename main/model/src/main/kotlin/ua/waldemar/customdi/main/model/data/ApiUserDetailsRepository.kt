package ua.waldemar.customdi.main.model.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository
import ua.waldemar.customdi.main.model.domain.UserInfoModel
import ua.waldemar.customdi.main.shared.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.shared.data.UserDataSource

internal class ApiUserDetailsRepository(
    private val userDataSource: UserDataSource,
) : UserDetailsRepository {

    override val userInfo: Flow<Result<UserInfoModel>> = userDataSource.userModelState
        .map { it.fold(
            onFailure = { Result.failure(it) },
            onSuccess = {
                Result.success(
                    UserInfoModel(
                        it.firstName,
                        it.lastName,
                        it.middleName
                    )
                )
            }
        ) }


    override suspend fun refreshCurrentUser() {
        userDataSource.refreshCurrentUser()
    }
}
