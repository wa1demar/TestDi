package ua.waldemar.customdi.main.model.data

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.model.domain.UnexpectedErrorRepository
import ua.waldemar.customdi.main.shared.data.UnexpectedErrorHandler
import ua.waldemar.customdi.main.shared.domain.UnexpectedError

internal class ApiUnexpectedErrorRepository(
    errorHandler: UnexpectedErrorHandler,
) : UnexpectedErrorRepository {
    override val unexpectedError: Flow<UnexpectedError> = errorHandler.errors
}