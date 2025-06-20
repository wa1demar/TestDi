package ua.waldemar.customdi.api.services

import ua.waldemar.customdi.api.model.UserApiModel

internal class RequestService(
    private val stateService: StateService
): ApiService by ApiServiceImpl() {

    suspend fun getUser(
        expandOptions: List<String>?
    ): Result<UserApiModel> {
        // send request to server
        return Result.success(
            UserApiModel(
                "first", "last", "one more"
            )
        )
    }

    suspend fun logout(): Result<Any> {
        stateService.updateState(ApiState.Unauthorized)
        return Result.success("any")
    }
}