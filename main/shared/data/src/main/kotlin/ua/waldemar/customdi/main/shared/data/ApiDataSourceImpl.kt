package ua.waldemar.customdi.main.shared.data

import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.model.UserApiModel

internal class ApiDataSourceImpl : ApiDataSource {
    override suspend fun getUser(expandOptions: List<String>?): Result<UserApiModel> {
        return AccessAPI.getUser(expandOptions)
    }
}