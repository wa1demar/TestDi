package ua.waldemar.customdi.api

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.api.model.UserApiModel
import ua.waldemar.customdi.api.services.ApiConfiguration
import ua.waldemar.customdi.api.services.ApiState
import ua.waldemar.customdi.api.services.LaunchOptions
import ua.waldemar.customdi.api.services.ServiceFactory
import ua.waldemar.customdi.api.services.ServiceManager
import ua.waldemar.customdi.api.services.ServiceRegistry
import ua.waldemar.customdi.api.services.authService
import ua.waldemar.customdi.api.services.requestService
import ua.waldemar.customdi.api.services.stateService

object AccessAPI {

    private val serviceManager by lazy {
        ServiceManager(ServiceRegistry())
    }

    @get:JvmSynthetic
    val stateFlow: Flow<ApiState>
        get() = serviceManager.stateService.states

    @get:JvmSynthetic
    val state: ApiState
        get() = serviceManager.stateService.latestState

    @JvmSynthetic
    fun setup(
        context: Context,
        configuration: ApiConfiguration,
    ) {
        val factory = ServiceFactory(context.applicationContext, configuration)
        serviceManager.registerServices(factory)
        serviceManager.stateService.updateState(ApiState.Initialized)
    }

    @JvmSynthetic
    fun launch(launchOptions: LaunchOptions = LaunchOptions()) {
        serviceManager.launchServices(launchOptions)
    }

    @JvmSynthetic
    suspend fun signIn(login: String, password: String): Result<String> {
        return serviceManager.authService.signIn(login, password)
    }

    @JvmSynthetic
    suspend fun signUp(login: String, password: String): Result<Any> {
        return serviceManager.authService.signUp(login, password)
    }

    @JvmSynthetic
    suspend fun logout(): Result<Any> {
        return serviceManager.requestService.logout()
    }

    @JvmSynthetic
    suspend fun getUser(
        expandOptions: List<String>?,
    ): Result<UserApiModel> {
        return serviceManager.requestService.getUser(expandOptions)
    }
}