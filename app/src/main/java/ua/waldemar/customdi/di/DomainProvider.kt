package ua.waldemar.customdi.di

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import ua.waldemar.customdi.di.init.InitUiInteractor
import ua.waldemar.customdi.domain.SignInInteractor
import ua.waldemar.customdi.domain.SignOutInteractor
import ua.waldemar.customdi.domain.SignUpInteractor

object DomainProvider {

    private lateinit var dataProvider: DataProvider

    val signInInteractor: SignInInteractor
        get() = with(dataProvider) {
            SignInInteractor(signInRepository)
        }

    val signUpInteractor: SignUpInteractor
        get() = SignUpInteractor(dataProvider.signUpRepository)

    val signOutInteractor: SignOutInteractor
        get() = SignOutInteractor()

    fun setup(appContext: Context, applicationScope: CoroutineScope) {
        dataProvider = DataProvider(
            appContext,
            applicationScope,
        )
    }

    val initUiInteractor: InitUiInteractor
        get() = with(dataProvider) {
            InitUiInteractor(initUiRepository)
        }

    // other interactors
}