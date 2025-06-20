package ua.waldemar.customdi.api.services

sealed class ApiState {
    object Initial : ApiState()
    object Initialized : ApiState()
    object Authorized : ApiState()
    object Authorizing : ApiState()
    object Unauthorized : ApiState()
    data class UnexpectedError(val t: Throwable) : ApiState()
}