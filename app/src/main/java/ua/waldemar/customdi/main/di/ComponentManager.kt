package ua.waldemar.customdi.main.di

import android.content.Context
import ua.waldemar.customdi.appfeature.forgot.presentation.di.ForgotPasswordComponent

class ComponentManager(private val appContext: Context) {

    private var _forgotPasswordComponent: ForgotPasswordComponent? = null

    val forgotPasswordComponent: ForgotPasswordComponent
        get() {
            if (_forgotPasswordComponent == null) {
                _forgotPasswordComponent = ForgotPasswordComponent(appContext)
            }
            return _forgotPasswordComponent!!
        }

    fun clearForgotPasswordComponent() {
        _forgotPasswordComponent = null
    }
}