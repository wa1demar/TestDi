package ua.waldemar.customdi.main.model.domain

class SignInInteractor internal constructor(
    private val signInRepository: SignInRepository
) {

    suspend fun signIn(email: String, password: String): Result<String> {
        return signInRepository.signIn(email, password)
    }
}