package ua.waldemar.customdi.main.public.data

import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.main.publicapi.domain.signin.SignInRepository

class ApiSignInRepository internal constructor() : SignInRepository {
    override suspend fun signIn(email: String, password: String): Result<String> {
        return AccessAPI.signIn(email, password)
    }
}