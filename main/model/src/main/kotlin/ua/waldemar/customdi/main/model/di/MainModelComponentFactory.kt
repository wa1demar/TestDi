package ua.waldemar.customdi.main.model.di

import android.content.Context
import ua.waldemar.customdi.main.model.di.modules.DataModule
import ua.waldemar.customdi.main.model.di.modules.DomainModule
import ua.waldemar.customdi.main.shared.data.DataSourceModule

object MainModelComponentFactory {

    fun create(context: Context, userId: String): MainModelComponent {
        val dataSourceModule = provideDataSourceModule()
        val dataModule = provideDataModule(dataSourceModule)
        val domainModule = provideDomainModule(dataModule)

        return MainModelComponent(domainModule)
    }

    private fun provideDataSourceModule(): DataSourceModule {
        return DataSourceModule()
    }

    private fun provideDataModule(dataSourceModule: DataSourceModule): DataModule {
        return DataModule(dataSourceModule)
    }

    private fun provideDomainModule(dataModule: DataModule): DomainModule {
        return DomainModule(
            dataModule.unexpectedErrorRepository,
            dataModule.userRepository
        )
    }
}