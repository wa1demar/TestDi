package ua.waldemar.customdi.main.model.data.api

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.model.UserApiModel
import ua.waldemar.customdi.api.services.ApiState

class ApiDataSourceImpl : ApiDataSource {

    override val stateFlow: Flow<ApiState> = AccessAPI.stateFlow

    override suspend fun getUser(expandOptions: List<String>?): Result<UserApiModel> {
        return AccessAPI.getUser(expandOptions)
    }
}