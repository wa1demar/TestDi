package ua.waldemar.customdi.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

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