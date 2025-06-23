package ua.waldemar.customdi.main.model.data

import android.content.Context
import android.util.Log

class DeviceIdSource(private val context: Context) {
    init {
        Log.d("LogLifecycle", "DeviceIdSource created: $this")
    }
    val deviceId: String
        get() {
            // get from chared prefs
            return "fake id"
        }
}