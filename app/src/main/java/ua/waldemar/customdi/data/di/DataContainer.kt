package ua.waldemar.customdi.data.di

import android.content.Context
import ua.waldemar.customdi.data.ApiSignInRepository
import ua.waldemar.customdi.data.ApiSignUpRepository
import ua.waldemar.customdi.data.firebase.FirebaseDynamicInitEventDataSource
import ua.waldemar.customdi.data.firebase.FirebaseInitUiRepository
import ua.waldemar.customdi.di.init.InitUiRepository
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.domain.SignUpRepository

class DataContainer(appContext: Context) {

    private val dynamicLinkDataSource: FirebaseDynamicInitEventDataSource by lazy {
        FirebaseDynamicInitEventDataSource(appContext)
    }

    val signInRepository: SignInRepository by lazy {
        ApiSignInRepository()
    }

    val signUpRepository: SignUpRepository by lazy {
        ApiSignUpRepository()
    }

    val initUiRepository: InitUiRepository by lazy {
        FirebaseInitUiRepository(dynamicLinkDataSource)
    }
}