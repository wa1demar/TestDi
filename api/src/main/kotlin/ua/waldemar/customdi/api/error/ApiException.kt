package ua.waldemar.customdi.api.error

import java.io.IOException

class EwaApiError(message: String) : RuntimeException(message)

open class ApiException(
    val code: String,
    message: String,
    cause: Throwable? = null
) : IOException(message, cause)

class ApiConnectionException(message: String, cause: IOException) : ApiException(
    ApiErrorCodes.CONNECTION_FAILURE,
    message,
    cause
)

class ApiRefreshAuthException(message: String, cause: Throwable? = null) : ApiException(
    ApiErrorCodes.REFRESH_AUTH_FAILURE,
    message,
    cause
)