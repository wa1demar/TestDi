package ua.waldemar.customdi.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.di.init.InitEvent
import ua.waldemar.customdi.di.init.InitUiInteractor

class AppViewModel() : ViewModel() {

    private val _getRemoteConfig = Channel<Boolean>()
    val getRemoteConfig: ReceiveChannel<Boolean> = _getRemoteConfig

    init {
        viewModelScope.launch {
            // todo: get remote config
            _getRemoteConfig.send(true)
        }
    }
}