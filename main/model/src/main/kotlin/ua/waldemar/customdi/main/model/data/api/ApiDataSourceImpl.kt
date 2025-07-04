package ua.waldemar.customdi.main.model.data.api

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.services.ApiState

class ApiDataSourceImpl : ApiDataSource {

    override val stateFlow: Flow<ApiState> = AccessAPI.stateFlow
}