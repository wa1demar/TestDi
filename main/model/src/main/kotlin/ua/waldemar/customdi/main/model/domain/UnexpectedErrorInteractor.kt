package ua.waldemar.customdi.main.model.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow

class UnexpectedErrorInteractor internal constructor(
    unexpectedErrorRepository: UnexpectedErrorRepository,
) {

    init {
        Log.d("LogLifecycle", "UnexpectedErrorInteractor created: $this")
        Log.d("LogLifecycle", "SignUpInteractor@unexpectedErrorRepository: $unexpectedErrorRepository")
    }
    val unexpectedError: Flow<UnexpectedError> = unexpectedErrorRepository.unexpectedError
}