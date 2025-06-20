package ua.waldemar.customdi.main.model.di

import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.model.domain.UserDetailsRepository

interface UiDataModule {
    val unexpectedErrorRepository: UnexpectedErrorRepository
    val userDetailsRepository: UserDetailsRepository
}