package ua.waldemar.customdi.main.model.data

import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.main.model.domain.SignInRepository

class ApiSignInRepository internal constructor() : SignInRepository {
    override suspend fun signIn(email: String, password: String): Result<String> {
        return AccessAPI.signIn(email, password)
    }
}