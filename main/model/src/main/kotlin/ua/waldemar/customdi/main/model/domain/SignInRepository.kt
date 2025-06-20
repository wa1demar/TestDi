package ua.waldemar.customdi.main.model.domain

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Result<String>
}
