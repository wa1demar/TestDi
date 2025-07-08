package ua.waldemar.customdi.api.services

import kotlinx.coroutines.delay
import ua.waldemar.customdi.api.model.HistoryModel
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

    suspend fun getHistory(): Result<List<HistoryModel>> {
        // send request to server
        delay(1000)
        return Result.success(
            listOf(
                HistoryModel("1", "Item 1"),
                HistoryModel("2", "Item 2"),
                HistoryModel("3", "Item 3"),
            )
        )
    }

    suspend fun logout(): Result<Any> {
        stateService.updateState(ApiState.Unauthorized)
        return Result.success("any")
    }
}