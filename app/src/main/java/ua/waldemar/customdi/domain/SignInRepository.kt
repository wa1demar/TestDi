package ua.waldemar.customdi.domain

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.appfeature.common.ExecStatus

interface SignInRepository {
    val userId: Flow<String>

    suspend fun signIn(email: String, password: String): ExecStatus
}