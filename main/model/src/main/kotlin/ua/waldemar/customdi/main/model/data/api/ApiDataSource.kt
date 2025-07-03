package ua.waldemar.customdi.main.model.data.api

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.api.model.UserApiModel
import ua.waldemar.customdi.api.services.ApiState

interface ApiDataSource {
    val stateFlow: Flow<ApiState>

    suspend fun getUser(expandOptions: List<String>?): Result<UserApiModel>
}