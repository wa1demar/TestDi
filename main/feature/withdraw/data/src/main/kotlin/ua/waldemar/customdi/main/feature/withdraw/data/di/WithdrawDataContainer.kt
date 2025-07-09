package ua.waldemar.customdi.main.feature.withdraw.data.di

import ua.waldemar.customdi.main.feature.withdraw.data.FakeWithdrawRepositoryImpl
import ua.waldemar.customdi.main.feature.withdraw.domain.WithdrawRepository

class WithdrawDataContainer {
    val withdrawRepository: WithdrawRepository by lazy {
        FakeWithdrawRepositoryImpl()
    }
}