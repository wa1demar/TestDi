package ua.waldemar.customdi.main.model.di

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.services.ApiConfiguration
import ua.waldemar.customdi.main.model.data.DeviceIdSource

object MainModelComponent {
    private lateinit var mainModuleScope: MainUiModuleScope

    lateinit var appDomainModule: AppDomainModule
        private set

    lateinit var appDataModule: AppDataModule
        private set

    val mainUiDomainModule: MainUiDomainModule
        get() = mainModuleScope.domainModule

    val isInitialized: Boolean
        get() = ::mainModuleScope.isInitialized && ::appDomainModule.isInitialized

    suspend fun setUp(context: Context, apiKey: String, allowPinning: Boolean) {
        appDataModule = AppDataModule(context)
        appDomainModule = AppDomainModule(appDataModule)
        AccessAPI.apply {
            val deviceId = withContext(Dispatchers.IO) { DeviceIdSource(context).deviceId }
            setup(context, ApiConfiguration(apiKey, deviceId, allowPinning))
            launch()
        }
    }

    fun createUiScope(context: Context, userId: String) {
        if (isInitialized) mainModuleScope.close()
        mainModuleScope = MainUiModuleScope(
            context,
            userId,
            appDomainModule.appDataModule,
        )
    }
}