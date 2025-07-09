package ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step2

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.WithdrawDataHolder

class WithdrawFormStep2ViewModel(
    private val dataHolder: WithdrawDataHolder
) : ViewModel() {

    var input: String?
        get() = dataHolder.step2Data
        set(value) { dataHolder.step2Data = value }
}