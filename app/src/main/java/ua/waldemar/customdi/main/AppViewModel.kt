package ua.waldemar.customdi.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.di.init.InitEvent
import ua.waldemar.customdi.di.init.InitUiInteractor

class AppViewModel(
//    private val initUiInteractor: InitUiInteractor,
) : ViewModel() {

//    val initEvent: Flow<InitEvent> = initUiInteractor.initEvent

    fun onCreated(data: String?) {
//        viewModelScope.launch { initUiInteractor.initUi(data) }
    }
}