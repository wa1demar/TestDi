package ua.waldemar.customdi.main.feature.history.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.main.feature.history.domain.GetHistoryUseCase
import ua.waldemar.customdi.main.feature.history.domain.HistoryItem

class HistoryViewModel(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    private val _historyItems = MutableStateFlow<List<HistoryItem>>(emptyList())
    val historyItems = _historyItems.asStateFlow()

    init {
        viewModelScope.launch {
            getHistoryUseCase().onSuccess {
                _historyItems.emit(it)
            }
        }
    }
}