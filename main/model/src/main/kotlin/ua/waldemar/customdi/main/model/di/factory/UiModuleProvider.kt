package ua.waldemar.customdi.main.model.di.factory

import android.content.Context
import ua.waldemar.customdi.main.model.di.AppDataModule
import ua.waldemar.customdi.main.model.di.MainUiDataModule
import ua.waldemar.customdi.main.model.di.UiDataModule

object UiModuleProvider {
    private val defaultConfiguration = UiModuleConfiguration()

    internal var configuration = defaultConfiguration
        private set

    private class UiDataModuleFactoryImpl : UiDataModuleFactory {
        override fun create(
            context: Context,
            userId: String,
            appDataModule: AppDataModule,
        ): UiDataModule {
            return MainUiDataModule(
                context,
                userId,
                appDataModule
            )
        }
    }

    class UiModuleConfiguration(
        val uiDataModuleFactory: UiDataModuleFactory = UiDataModuleFactoryImpl()
    )
}