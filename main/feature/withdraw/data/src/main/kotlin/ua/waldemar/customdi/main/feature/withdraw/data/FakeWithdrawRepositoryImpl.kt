package ua.waldemar.customdi.main.feature.withdraw.data

import kotlinx.coroutines.delay
import ua.waldemar.customdi.main.feature.withdraw.domain.WithdrawRepository

class FakeWithdrawRepositoryImpl : WithdrawRepository {
    override suspend fun withdraw(data1: String, data2: String): Boolean {
        delay(1000)
        return true // завжди успіх
    }
}