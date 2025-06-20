package ua.waldemar.customdi.main.model.di

import android.content.Context
import ua.waldemar.customdi.main.model.data.ApiSignInRepository
import ua.waldemar.customdi.main.model.data.ApiSignUpRepository
import ua.waldemar.customdi.main.model.domain.SignInRepository
import ua.waldemar.customdi.main.model.domain.SignUpRepository

class AppDataModule internal constructor(
    context: Context,
) {
    val signInRepository: SignInRepository by lazy {
        ApiSignInRepository()
    }

    val signUpRepository: SignUpRepository by lazy {
        ApiSignUpRepository()
    }
}