package ua.waldemar.customdi.main.feature.updatepassword.data

import kotlinx.coroutines.delay
import ua.waldemar.customdi.main.feature.updatepassword.domain.UpdatePasswordRepository

class UpdatePasswordRepositoryImpl : UpdatePasswordRepository {
    override suspend fun updatePassword(password: String): Boolean {
        delay(1000) // імітація мережі
        return password.length >= 6 // фейкова перевірка
    }
}