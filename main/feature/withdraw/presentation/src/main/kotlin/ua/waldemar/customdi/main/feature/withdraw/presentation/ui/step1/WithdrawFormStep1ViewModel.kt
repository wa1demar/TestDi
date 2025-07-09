package ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step1

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.WithdrawDataHolder

class WithdrawFormStep1ViewModel(
    private val dataHolder: WithdrawDataHolder
) : ViewModel() {
    var input: String?
        get() = dataHolder.step1Data
        set(value) { dataHolder.step1Data = value }
}