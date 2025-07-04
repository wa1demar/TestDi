package ua.waldemar.customdi.main.publicapi.domain.signin

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Result<String>
}
