package ua.waldemar.customdi.api.services

import android.content.Context

internal class ServiceFactory(
    private val appContext: Context,
    private val apiConfiguration: ApiConfiguration
) {
    fun createStateService(): StateService {
        return StateService()
    }

    fun createAuthService(
        stateService: StateService,
    ): AuthService {
        return AuthService(
            stateService,
        )
    }

    fun createRequestService(
        stateService: StateService,
    ): RequestService {
        return RequestService(
            stateService,
        )
    }
}