package ua.waldemar.customdi.main.di

import android.content.Context
import android.util.Log
import ua.waldemar.customdi.appfeature.forgot.presentation.di.ForgotPasswordComponent

class ComponentManager(private val appContext: Context) {

    private var _forgotPasswordComponent: ForgotPasswordComponent? = null

    fun getOrCreateForgotPasswordComponent(): ForgotPasswordComponent {
        Log.d("ForgotPasswordComponent", "create ForgotPasswordComponent scope")
        if (_forgotPasswordComponent == null) {
            Log.d("ForgotPasswordComponent", "ForgotPasswordComponent scope created")
            _forgotPasswordComponent = ForgotPasswordComponent(appContext)
        }
        return _forgotPasswordComponent!!
    }

    fun clearForgotPasswordComponent() {
        Log.d("ForgotPasswordComponent", "ForgotPasswordComponent scope cleared")
        _forgotPasswordComponent = null
    }
}