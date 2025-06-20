package ua.waldemar.customdi.main.model.domain

import kotlinx.coroutines.flow.Flow

interface UnexpectedErrorRepository {
    val unexpectedError: Flow<UnexpectedError>
}