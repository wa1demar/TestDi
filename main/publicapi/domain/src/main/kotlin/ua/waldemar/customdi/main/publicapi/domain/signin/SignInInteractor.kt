package ua.waldemar.customdi.main.publicapi.domain.signin

class SignInInteractor(
    private val signInRepository: SignInRepository
) {
    suspend fun signIn(email: String, password: String): Result<String> {
        return signInRepository.signIn(email, password)
    }
}