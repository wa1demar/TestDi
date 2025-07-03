package ua.waldemar.customdi.main.model.di

import android.content.Context
import ua.waldemar.customdi.main.model.data.ApiSignInRepository
import ua.waldemar.customdi.main.model.data.ApiSignUpRepository
import ua.waldemar.customdi.main.model.data.api.ApiDataSource
import ua.waldemar.customdi.main.model.data.api.ApiDataSourceImpl
import ua.waldemar.customdi.main.model.domain.SignInRepository
import ua.waldemar.customdi.main.model.domain.SignUpRepository

class AppDataModule internal constructor(
    context: Context,
) {
    val apiDataSource: ApiDataSource by lazy {
        ApiDataSourceImpl()
    }
    val signInRepository: SignInRepository by lazy {
        ApiSignInRepository()
    }

    val signUpRepository: SignUpRepository by lazy {
        ApiSignUpRepository()
    }
}