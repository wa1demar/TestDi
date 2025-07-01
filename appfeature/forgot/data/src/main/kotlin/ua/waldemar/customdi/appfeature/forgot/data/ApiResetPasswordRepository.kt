package ua.waldemar.customdi.appfeature.forgot.data

import kotlinx.coroutines.delay
import ua.waldemar.customdi.appfeature.common.ExecStatus
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordRepository

internal class ApiResetPasswordRepository : ResetPasswordRepository {
    override suspend fun requestOtp(email: String, resend: Boolean): ExecStatus {
        delay(2000)
        return ExecStatus.Success
    }
}