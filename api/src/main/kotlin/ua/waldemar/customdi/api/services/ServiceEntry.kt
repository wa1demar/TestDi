package ua.waldemar.customdi.api.services

internal data class ServiceEntry(
    val service: ApiService,
    val launchOnDemand: Boolean
) {
    var isLaunched: Boolean = false
}

internal fun ServiceEntry.launchEntryService(options: LaunchOptions) {
    service.launch(options)
    isLaunched = true
}

data class ApiConfiguration(val apiKey: String, val allowPinning: Boolean = false)

class LaunchOptions