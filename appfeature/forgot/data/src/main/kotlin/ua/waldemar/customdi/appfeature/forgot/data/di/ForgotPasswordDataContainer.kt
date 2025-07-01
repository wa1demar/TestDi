package ua.waldemar.customdi.appfeature.forgot.data.di

import android.content.Context
import ua.waldemar.customdi.appfeature.forgot.data.ApiResetPasswordRepository
import ua.waldemar.customdi.appfeature.password.domain.ResetPasswordRepository

class ForgotPasswordDataContainer(appContext: Context) {
    val resetPasswordRepository: ResetPasswordRepository by lazy {
        ApiResetPasswordRepository()
    }
}