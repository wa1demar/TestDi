package ua.waldemar.customdi.main.shared.data

import ua.waldemar.customdi.api.model.UserApiModel

internal interface ApiDataSource {
    suspend fun getUser(expandOptions: List<String>?): Result<UserApiModel>
}