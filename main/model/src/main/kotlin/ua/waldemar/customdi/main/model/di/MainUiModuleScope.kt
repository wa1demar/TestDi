package ua.waldemar.customdi.main.model.di

import android.content.Context
import ua.waldemar.customdi.main.model.data.ApiSignInRepository
import ua.waldemar.customdi.main.model.data.ApiSignUpRepository
import ua.waldemar.customdi.main.model.di.factory.UiModuleProvider
import ua.waldemar.customdi.main.model.domain.SignInRepository
import ua.waldemar.customdi.main.model.domain.SignUpRepository

internal class MainUiModuleScope(
    private val appContext: Context,
    private val userId: String,
    private val appDataModule: AppDataModule,
) : ModuleScope {
    private var _domainModule: MainUiDomainModule? = null

    val domainModule: MainUiDomainModule
        get() = _domainModule ?: MainUiDomainModule(
            this,
            UiModuleProvider.configuration
                .uiDataModuleFactory.create(
                    appContext,
                    userId,
                    appDataModule,
                )
        ).also { module -> _domainModule = module }

    override fun close() {
        _domainModule = null
    }
}