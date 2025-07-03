package ua.waldemar.customdi.main.model.di_v2

import android.content.Context
import android.util.Log
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.services.ApiConfiguration

object AccessApiManager {
    private var isInitialized = false

    fun setup(context: Context, apiKey: String, allowPinning: Boolean) {
        if (isInitialized) return
        AccessAPI.apply {
            setup(context, ApiConfiguration(apiKey, allowPinning))
            launch()
        }
        isInitialized = true
        Log.d("AccessApiManager", "AccessAPI initialized with key=$apiKey")
    }

    fun reset() {
        isInitialized = false
        // If AccessAPI has shutdown method, call it here
        Log.d("AccessApiManager", "AccessAPI state reset")
    }
}