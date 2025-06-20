package ua.waldemar.customdi.api.services

import kotlin.reflect.KClass

internal class ServiceRegistry: Iterable<ServiceEntry>  {
    private val entryMap = mutableMapOf<String, ServiceEntry>()

    override fun iterator(): Iterator<ServiceEntry> {
        return entryMap.values.iterator()
    }

    fun add(service: ApiService, launchOnDemand: Boolean = false) {
        entryMap[service::class.toString()] = ServiceEntry(service, launchOnDemand)
    }

    inline operator fun <reified T : ApiService> get(serviceClass: KClass<T>): ServiceEntry? {
        return entryMap[serviceClass.toString()]
    }
}