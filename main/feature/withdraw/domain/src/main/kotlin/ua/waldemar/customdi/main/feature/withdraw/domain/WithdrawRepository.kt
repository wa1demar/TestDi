package ua.waldemar.customdi.main.feature.withdraw.domain

interface WithdrawRepository {
    suspend fun withdraw(data1: String, data2: String): Boolean
} 