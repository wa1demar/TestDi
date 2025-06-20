package ua.waldemar.customdi.api.services

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class StateService: ApiService by ApiServiceImpl() {
    private val _states = MutableStateFlow<ApiState>(ApiState.Initial)

    val states: StateFlow<ApiState> = _states.asStateFlow()

    val latestState: ApiState
        get() = states.value

    fun updateState(state: ApiState) {
        _states.value = state
    }
}