package ua.waldemar.customdi.main.model.domain

import kotlinx.coroutines.flow.Flow

class UnexpectedErrorInteractor internal constructor(
    unexpectedErrorRepository: UnexpectedErrorRepository,
) {

    val unexpectedError: Flow<UnexpectedError> = unexpectedErrorRepository.unexpectedError
}