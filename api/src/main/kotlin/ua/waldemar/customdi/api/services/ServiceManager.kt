package ua.waldemar.customdi.api.services

import kotlin.reflect.KClass

internal class ServiceManager(private val registry: ServiceRegistry) {

    var servicesRegistered: Boolean = false
        private set
    inline fun <reified T : ApiService> getService(
        clazz: KClass<T>,
        options: LaunchOptions = LaunchOptions()
    ): T = registry[clazz]?.let { entry ->
        if (!entry.isLaunched) {
            entry.launchEntryService(options)
        }
        entry.service as? T
    } ?: throw RuntimeException("Failed to get ${clazz.simpleName} service. Likely the initialization of SDK was missed")

    fun registerServices(
        serviceFactory: ServiceFactory,
        launchOnDemand: Boolean = false
    ) {
        with(serviceFactory) {
            registry.apply {
                add(createStateService(), launchOnDemand)
                add(createAuthService(stateService), launchOnDemand)
                add(createRequestService(stateService), launchOnDemand)
            }
        }
        servicesRegistered = true
    }

    fun launchServices(options: LaunchOptions) {
        registry.forEach { entry ->
            if (!entry.launchOnDemand) {
                entry.launchEntryService(options)
            }
        }
    }
}

internal val ServiceManager.stateService: StateService
    get() = getService(StateService::class)

internal val ServiceManager.authService: AuthService
    get() = getService(AuthService::class)

internal val ServiceManager.requestService: RequestService
    get() = getService(RequestService::class)