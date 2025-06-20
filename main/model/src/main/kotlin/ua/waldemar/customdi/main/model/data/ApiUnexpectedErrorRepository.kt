package ua.waldemar.customdi.main.model.data

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.model.domain.UnexpectedError
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository

internal class ApiUnexpectedErrorRepository(
    errorHandler: UnexpectedErrorHandler,
) : UnexpectedErrorRepository {
    override val unexpectedError: Flow<UnexpectedError> = errorHandler.errors
}