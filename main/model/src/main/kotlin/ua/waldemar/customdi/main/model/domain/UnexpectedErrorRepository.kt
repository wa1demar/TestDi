package ua.waldemar.customdi.main.model.domain

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.shared.domain.UnexpectedError

interface UnexpectedErrorRepository {
    val unexpectedError: Flow<UnexpectedError>
}