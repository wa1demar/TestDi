package ua.waldemar.customdi.api.services

internal class AuthService(
    private val stateService: StateService,
) : ApiService {

    override fun launch(options: LaunchOptions) {
        stateService.updateState(ApiState.Unauthorized)
    }

    suspend fun signIn(login: String, password: String): Result<String> {
        return if (login == "vovam@paykey.com" && password == "Testing123!@") {
            Result.success("fakeUserId")
        } else {
            stateService.updateState(ApiState.Unauthorized)
            Result.failure(Exception("Wrong email or password"))
        }
    }

    suspend fun signUp(login: String, password: String): Result<Any> {
        return if (login != "vovam@paykey.com" && password != "Testing123!@") {
            Result.success("nothing")
        } else {
            Result.failure(Exception("Wrong email"))
        }
    }
}