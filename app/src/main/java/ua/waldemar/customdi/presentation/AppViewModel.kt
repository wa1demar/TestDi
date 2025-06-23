package ua.waldemar.customdi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.core.di.ScopeManager
import ua.waldemar.customdi.di.init.InitUiInteractor

class AppViewModel(
    private val initUiInteractor: InitUiInteractor,
) : ViewModel() {

    private val _isInitInProgress = MutableStateFlow(true)
    val isInitInProgress = _isInitInProgress.asStateFlow()

    init {
        initAccessUi()
    }

    private fun initAccessUi() = viewModelScope.launch {
        _isInitInProgress.emit(true)
        initUiInteractor.initUi()
        _isInitInProgress.emit(false)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                with(ScopeManager.getScope("app")) {
                    AppViewModel(get())
                }
            }
        }
    }
}