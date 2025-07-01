package ua.waldemar.customdi.domain

import ua.waldemar.customdi.appfeature.common.ExecStatus

interface SignUpRepository {
    suspend fun signUp(email: String, password: String): ExecStatus
}