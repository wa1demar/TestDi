package ua.waldemar.customdi.main.model.data

import android.content.Context

class DeviceIdSource(private val context: Context) {
    val deviceId: String
        get() {
            // get from chared prefs
            return "fake id"
        }
}