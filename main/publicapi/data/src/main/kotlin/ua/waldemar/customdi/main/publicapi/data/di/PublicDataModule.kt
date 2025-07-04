package ua.waldemar.customdi.main.public.data.di

import ua.waldemar.customdi.main.public.data.ApiSignInRepository
import ua.waldemar.customdi.main.public.data.ApiSignUpRepository
import ua.waldemar.customdi.main.publicapi.domain.signin.SignInRepository
import ua.waldemar.customdi.main.publicapi.domain.signup.SignUpRepository

class PublicDataModule {
    val signInRepository: SignInRepository by lazy {
        ApiSignInRepository()
    }

    val signUpRepository: SignUpRepository by lazy {
        ApiSignUpRepository()
    }
}