package ua.waldemar.customdi.main.di

import android.content.Context
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.data.di.DataContainer
import ua.waldemar.customdi.main.di.modules.AuthUseCaseModule
import ua.waldemar.customdi.main.di.modules.InitUseCaseModule

class AppContainer(appContext: Context) {

    private val dataContainer by lazy { DataContainer(appContext) }

    private val componentManager = ComponentManager(appContext)

    private val authUseCases by lazy {
        with(dataContainer) {
            AuthUseCaseModule(
                signInRepository,
                signUpRepository,
            )
        }
    }

    private val initUseCaseModule by lazy {
        with(dataContainer) {
            InitUseCaseModule(initUiRepository)
        }
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        componentManager.forgotPasswordComponent.viewModelCreators + baseCreators()
    }

    private fun baseCreators(): Map<Class<out ViewModel>, () -> ViewModel> {
        return AppViewModelFactoryContributor(authUseCases).provide().entries.associate { it.toPair() }
    }

    fun clearForgotPasswordScope() {
        componentManager.clearForgotPasswordComponent()
    }
}