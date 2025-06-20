package ua.waldemar.customdi.domain

interface SignUpRepository {
    suspend fun signUp(email: String, password: String): ExecStatus
}