package ua.waldemar.customdi.main.shared.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import ua.waldemar.customdi.api.AccessAPI
import ua.waldemar.customdi.api.error.ApiConnectionException
import ua.waldemar.customdi.api.error.ApiErrorCodes
import ua.waldemar.customdi.api.error.ApiException
import ua.waldemar.customdi.api.error.ApiRefreshAuthException
import ua.waldemar.customdi.api.services.ApiState
import ua.waldemar.customdi.main.shared.domain.UnexpectedError

class UnexpectedErrorHandler() {
    private val _errors = MutableSharedFlow<UnexpectedError>(replay = 1)
    val errors: Flow<UnexpectedError> = merge(
        _errors,
        AccessAPI.stateFlow
            .filterIsInstance<ApiState.UnexpectedError>()
            .mapNotNull { state -> state.t.asUnexpectedErrorOrNull() },
    )

    suspend fun handle(unexpectedError: UnexpectedError) {
        _errors.emit(unexpectedError)
    }
}

fun Throwable.asUnexpectedErrorOrNull(
    vararg expectedErrorCode: String
): UnexpectedError? {
    return when {
        this is ApiRefreshAuthException || (this is ApiConnectionException &&
                cause is ApiRefreshAuthException) -> UnexpectedError.AuthFailure

        this is ApiConnectionException -> UnexpectedError.Network.NoConnection
        this is ApiException -> when (code) {
            in expectedErrorCode -> null
            ApiErrorCodes.INVALID_PK_TOKEN -> UnexpectedError.InvalidPKToken
            ApiErrorCodes.TOO_MANY_REQUESTS -> UnexpectedError.TooManyRequests
            ApiErrorCodes.USER_NOT_ACTIVE,
            ApiErrorCodes.EMPLOYER_NOT_ACTIVE -> UnexpectedError.UserStatusIssue

            ApiErrorCodes.EMAIL_NOT_VERIFIED -> UnexpectedError.EmailNotVerified
            ApiErrorCodes.BANK_NOT_APPROVED -> UnexpectedError.BankNotApproved
            ApiErrorCodes.USER_NOT_CONSENTED -> UnexpectedError.UserNotConsented
            ApiErrorCodes.TOKEN_IS_BLOCKED,
            ApiErrorCodes.TOKEN_IS_NOT_VALID -> UnexpectedError.AuthFailure

            else -> UnexpectedError.Network.General(message)
        }

        else -> UnexpectedError.Network.General()
    }
}

fun Result<*>.asUnexpectedErrorOrNull(
    vararg expectedErrorCode: String
): UnexpectedError? {
    return exceptionOrNull()?.asUnexpectedErrorOrNull(*expectedErrorCode)
}

suspend fun <T> Result<T>.onUnexpectedErrorNull(
    vararg expectedErrorCode: String,
    onUnexpectedError: suspend (error: UnexpectedError) -> Unit
): Result<T>? {
    return when (val error = asUnexpectedErrorOrNull(*expectedErrorCode)) {
        null -> this
        else -> {
            onUnexpectedError(error)
            null
        }
    }
}

suspend fun <T> Result<T>.onUnexpectedError(
    vararg expectedErrorCode: String,
    onUnexpectedError: suspend (error: UnexpectedError) -> Unit
): Result<T> = apply {
    asUnexpectedErrorOrNull(*expectedErrorCode)?.let { error ->
        onUnexpectedError(error)
    }
}

suspend fun <T> Result<T>.onUnexpectedErrorOrNull(
    vararg expectedErrorCode: String,
    onUnexpectedError: suspend (error: UnexpectedError, result: Result<T>) -> Result<T>?
): Result<T>? = when (val error = asUnexpectedErrorOrNull(*expectedErrorCode)) {
    null -> this
    else -> onUnexpectedError(error, this)
}