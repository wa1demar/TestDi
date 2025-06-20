package ua.waldemar.customdi.di.init

sealed class InitEvent {
    class Auth(val token: String) : InitEvent()
    object UnAuth : InitEvent()
}
