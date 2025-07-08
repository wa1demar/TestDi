package ua.waldemar.customdi.main.feature.updatepassword.domain

class UpdatePasswordUseCase(
    private val repository: UpdatePasswordRepository
) {
    suspend operator fun invoke(password: String): Boolean {
        return repository.updatePassword(password)
    }
}