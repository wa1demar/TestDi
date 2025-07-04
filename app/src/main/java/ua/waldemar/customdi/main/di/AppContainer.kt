package ua.waldemar.customdi.main.di

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.appfeature.forgot.presentation.di.ForgotPasswordComponent
import ua.waldemar.customdi.data.di.DataContainer
import ua.waldemar.customdi.main.di.modules.AuthUseCaseModule

class AppContainer(private val appContext: Context) {

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

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel>
        get() {
            Log.d("AppContainer", "Dynamically composing ViewModel creators...")
            val creators = baseCreators().toMutableMap()
            if (componentManager.has(ScopeKey.ForgotPassword)) {
                val forgotPasswordComponent = componentManager.getOrCreate(ScopeKey.ForgotPassword) {
                    Log.d("AppContainer", "ForgotPasswordComponent created")
                    ForgotPasswordComponent(appContext)
                }
                creators.putAll(forgotPasswordComponent.viewModelCreators)
            }
            return creators
        }

    private fun baseCreators(): Map<Class<out ViewModel>, () -> ViewModel> {
        return AppViewModelFactoryContributor(authUseCases).provide().entries.associate { it.toPair() }
    }

    fun getOrCreateForgotPasswordComponent(): ForgotPasswordComponent {
        return componentManager.getOrCreate(ScopeKey.ForgotPassword) {
            Log.d("ForgotPasswordComponent", "ForgotPasswordComponent scope created explicitly")
            ForgotPasswordComponent(appContext)
        }
    }

    fun clearForgotPasswordScope() {
        Log.d("ForgotPasswordComponent", "ForgotPasswordComponent scope cleared")
        componentManager.clear(ScopeKey.ForgotPassword)
    }
}