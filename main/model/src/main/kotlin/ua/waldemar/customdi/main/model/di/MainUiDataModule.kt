package ua.waldemar.customdi.main.model.di

import android.content.Context
import ua.waldemar.customdi.main.model.data.ApiUnexpectedErrorRepository
import ua.waldemar.customdi.main.model.data.ApiUserDetailsRepository
import ua.waldemar.customdi.main.model.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

internal class MainUiDataModule(
    private val context: Context,
    userId: String,
    private val appDataModule: AppDataModule,
) : UiDataModule {

    private val errorHandler: UnexpectedErrorHandler by lazy {
        UnexpectedErrorHandler()
    }

    override val unexpectedErrorRepository: UnexpectedErrorRepository by lazy {
        ApiUnexpectedErrorRepository(errorHandler)
    }

    override val userDetailsRepository: UserDetailsRepository by lazy {
        ApiUserDetailsRepository(errorHandler)
    }

}