package ua.waldemar.customdi.main.publicapi.domain.signup

class SignUpInteractor(private val signUpRepository: SignUpRepository) {
    suspend fun signUp(email: String, password: String): Result<Any> {
        return signUpRepository.signUp(email, password)
    }
}