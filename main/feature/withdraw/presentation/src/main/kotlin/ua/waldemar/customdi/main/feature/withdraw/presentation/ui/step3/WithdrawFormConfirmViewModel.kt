package ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ua.waldemar.customdi.main.feature.withdraw.domain.WithdrawUseCase
import ua.waldemar.customdi.main.feature.withdraw.presentation.WithdrawDataHolder

class WithdrawFormConfirmViewModel(
    private val dataHolder: WithdrawDataHolder,
    private val useCase: WithdrawUseCase
) : ViewModel() {
    private val _success = MutableStateFlow<Boolean?>(null)
    val success = _success.asStateFlow()

    val data: StateFlow<Pair<String, String>> =
        flowOf((dataHolder.step1Data ?: "") to (dataHolder.step2Data ?: ""))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "" to "")

    fun confirmWithdraw() {
        viewModelScope.launch {
            val step1Data = dataHolder.step1Data
            val step2Data = dataHolder.step2Data
            if (step1Data.isNullOrEmpty().not() && step2Data.isNullOrEmpty().not()) {
                val result = useCase(step1Data, step2Data)
                _success.value = result
            }
        }
    }
}