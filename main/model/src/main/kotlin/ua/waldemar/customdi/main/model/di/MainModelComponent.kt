package ua.waldemar.customdi.main.model.di

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.services.ApiConfiguration
import ua.waldemar.customdi.main.model.data.DeviceIdSource

object MainModelComponent {

    suspend fun setUp(context: Context, apiKey: String, allowPinning: Boolean) {
        AccessAPI.apply {
            val deviceId = withContext(Dispatchers.IO) { DeviceIdSource(context).deviceId }
            setup(context, ApiConfiguration(apiKey, deviceId, allowPinning))
            launch()
        }
    }
}