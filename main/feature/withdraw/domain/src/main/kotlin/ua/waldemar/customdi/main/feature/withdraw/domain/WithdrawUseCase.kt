package ua.waldemar.customdi.main.feature.withdraw.domain

class WithdrawUseCase(private val repo: WithdrawRepository) {
    suspend operator fun invoke(
        data1: String,
        data2: String
    ): Boolean = repo.withdraw(data1, data2)
} 