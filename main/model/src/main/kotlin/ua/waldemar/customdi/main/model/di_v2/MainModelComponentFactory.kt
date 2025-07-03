package ua.waldemar.customdi.main.model.di_v2

import android.content.Context
import ua.waldemar.customdi.main.model.di_v2.modules.DataModule
import ua.waldemar.customdi.main.model.di_v2.modules.DataSourceModule
import ua.waldemar.customdi.main.model.di_v2.modules.DomainModule

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