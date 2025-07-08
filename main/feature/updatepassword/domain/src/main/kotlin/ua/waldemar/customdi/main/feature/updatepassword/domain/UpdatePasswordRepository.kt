package ua.waldemar.customdi.main.feature.updatepassword.domain

interface UpdatePasswordRepository {
    suspend fun updatePassword(password: String): Boolean
}