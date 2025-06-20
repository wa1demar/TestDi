package ua.waldemar.customdi.main.model.di.factory

import android.content.Context
import ua.waldemar.customdi.main.model.di.AppDataModule
import ua.waldemar.customdi.main.model.di.UiDataModule

interface UiDataModuleFactory {

    fun create(
        context: Context,
        userId: String,
        appDataModule: AppDataModule,
    ): UiDataModule
}