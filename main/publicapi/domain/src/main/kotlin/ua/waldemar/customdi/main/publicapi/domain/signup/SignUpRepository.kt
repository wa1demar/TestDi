package ua.waldemar.customdi.main.publicapi.domain.signup

interface SignUpRepository {
    suspend fun signUp(email: String, password: String): Result<Any>
}