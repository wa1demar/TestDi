package ua.waldemar.customdi.main.model.domain

class SignUpInteractor internal constructor(private val signUpRepository: SignUpRepository) {
    suspend fun signUp(email: String, password: String): Result<Any> {
        return signUpRepository.signUp(email, password)
    }
}