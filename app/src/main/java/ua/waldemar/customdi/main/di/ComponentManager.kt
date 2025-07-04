package ua.waldemar.customdi.main.di

import android.content.Context
import android.util.Log
import ua.waldemar.customdi.appfeature.forgot.presentation.di.ForgotPasswordComponent
import kotlin.collections.containsKey

class ComponentManager(private val appContext: Context) {

    private val components = mutableMapOf<ScopeKey, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getOrCreate(scope: ScopeKey, factory: () -> T): T {
        return components.getOrPut(scope) { factory() } as T
    }

    fun clear(scope: ScopeKey) {
        components.remove(scope)
    }

    fun clearAll() {
        components.clear()
    }

    fun has(scope: ScopeKey): Boolean {
        return components.containsKey(scope)
    }
}