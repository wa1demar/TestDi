package ua.waldemar.customdi.domain

import android.util.Log

class SignUpInteractor(
    private val authRepository: SignUpRepository,
) {
    init {
        Log.d("LogLifecycle", "SignUpInteractor created: $this")
        Log.d("LogLifecycle", "SignInInteractor@authRepository: $authRepository")
    }
    suspend fun signUp(email: String, password: String): ExecStatus {
        return authRepository.signUp(email, password)
    }
}