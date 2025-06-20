package ua.waldemar.customdi.di

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import ua.waldemar.customdi.data.ApiSignInRepository
import ua.waldemar.customdi.data.ApiSignUpRepository
import ua.waldemar.customdi.data.firebase.FirebaseDynamicInitEventDataSource
import ua.waldemar.customdi.data.firebase.FirebaseInitUiRepository
import ua.waldemar.customdi.di.init.InitUiRepository
import ua.waldemar.customdi.domain.SignInRepository
import ua.waldemar.customdi.domain.SignUpRepository
import kotlin.getValue

class DataProvider(
    private val appContext: Context,
    private val applicationScope: CoroutineScope,
) {

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

    // other repositories
}