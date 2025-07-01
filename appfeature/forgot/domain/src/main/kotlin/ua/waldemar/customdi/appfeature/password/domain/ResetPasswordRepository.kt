package ua.waldemar.customdi.appfeature.password.domain

import ua.waldemar.customdi.appfeature.common.ExecStatus

interface ResetPasswordRepository {
    suspend fun requestOtp(email: String, resend: Boolean): ExecStatus
}