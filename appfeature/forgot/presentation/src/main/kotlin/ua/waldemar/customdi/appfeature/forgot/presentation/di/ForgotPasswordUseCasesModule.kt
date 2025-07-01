package ua.waldemar.customdi.appfeature.forgot.presentation.di

import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordInteractor
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordRepository

internal class ForgotPasswordUseCasesModule(
    private val resetPasswordRepository: ResetPasswordRepository
) {
    val resetPasswordInteractor get() = ResetPasswordInteractor(resetPasswordRepository)
}