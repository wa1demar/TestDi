package ua.waldemar.customdi.data.di

import android.content.Context
import ua.waldemar.customdi.data.ApiSignInRepository
import ua.waldemar.customdi.data.ApiSignUpRepository
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.domain.SignUpRepository

class DataContainer(appContext: Context) {

    val signInRepository: SignInRepository by lazy {
        ApiSignInRepository()
    }

    val signUpRepository: SignUpRepository by lazy {
        ApiSignUpRepository()
    }
}