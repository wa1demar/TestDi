package ua.waldemar.customdi.domain

sealed class ExecStatus {
    object Idle : ExecStatus()
    object InProgress : ExecStatus()
    object Success : ExecStatus()
    abstract class Failed : ExecStatus() {
        object TooManyRequests : Failed()
        object NotMeetPasswordRequirements : Failed()
        object General : Failed()
        object Network : Failed()
    }
}