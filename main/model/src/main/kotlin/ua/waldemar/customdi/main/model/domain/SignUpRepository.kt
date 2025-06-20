package ua.waldemar.customdi.main.model.domain

interface SignUpRepository {
    suspend fun signUp(email: String, password: String): Result<Any>
}