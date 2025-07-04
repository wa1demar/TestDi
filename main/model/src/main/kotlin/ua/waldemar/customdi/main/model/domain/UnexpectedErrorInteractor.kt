package ua.waldemar.customdi.main.model.domain

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.shared.domain.UnexpectedError

class UnexpectedErrorInteractor internal constructor(
    unexpectedErrorRepository: UnexpectedErrorRepository,
) {

    val unexpectedError: Flow<UnexpectedError> = unexpectedErrorRepository.unexpectedError
}