package ua.waldemar.customdi.main.shared.domain

sealed class UnexpectedError {
    sealed class Network : UnexpectedError() {
        data class General(val message: String? = null) : Network()
        data object NoConnection : Network()
    }

    data object InvalidPKToken : UnexpectedError()
    data object TooManyRequests : UnexpectedError()
    data object EmailNotVerified : UnexpectedError()
    data object BankNotApproved : UnexpectedError()
    data object UserStatusIssue : UnexpectedError()
    data object UserNotConsented : UnexpectedError()
    data object AuthFailure : UnexpectedError()
}