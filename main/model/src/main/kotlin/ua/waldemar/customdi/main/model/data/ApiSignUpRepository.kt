package ua.waldemar.customdi.main.model.data

import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.main.model.domain.SignUpRepository

class ApiSignUpRepository : SignUpRepository {
    override suspend fun signUp(email: String, password: String): Result<Any> {
        return AccessAPI.signUp(email, password)
    }
}