package ua.waldemar.customdi.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow

class SignInInteractor(
    private val signInRepository: SignInRepository,
) {

    init {
        Log.d("LogLifecycle", "SignInInteractor created: $this")
        Log.d("LogLifecycle", "SignInInteractor@signInRepository: $signInRepository")
    }
    val userId: Flow<String> = signInRepository.userId

    suspend fun signIn(email: String, password: String): ExecStatus =
        signInRepository.signIn(email, password).also { authStatus ->
            if (authStatus == ExecStatus.Success) {
                // update saved credentials
            }
        }
}