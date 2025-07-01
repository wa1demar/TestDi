package ua.waldemar.customdi.domain

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.appfeature.common.ExecStatus

class SignInInteractor(
    private val signInRepository: SignInRepository,
) {

    val userId: Flow<String> = signInRepository.userId

    suspend fun signIn(email: String, password: String): ExecStatus =
        signInRepository.signIn(email, password).also { authStatus ->
            if (authStatus == ExecStatus.Success) {
                // update saved credentials
            }
        }
}