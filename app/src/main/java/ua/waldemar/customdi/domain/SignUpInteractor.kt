package ua.waldemar.customdi.domain

import ua.waldemar.customdi.appfeature.common.ExecStatus

class SignUpInteractor(
    private val authRepository: SignUpRepository,
) {
    suspend fun signUp(email: String, password: String): ExecStatus {
        return authRepository.signUp(email, password)
    }
}