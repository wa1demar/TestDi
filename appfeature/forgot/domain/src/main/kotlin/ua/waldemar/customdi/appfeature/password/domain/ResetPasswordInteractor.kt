package ua.waldemar.customdi.appfeature.password.domain

import ua.waldemar.customdi.appfeature.common.ExecStatus

class ResetPasswordInteractor(
    private val repository: ResetPasswordRepository,
) {
    suspend fun requestOtp(email: String, resend: Boolean): ExecStatus =
        repository.requestOtp(email, resend)
}