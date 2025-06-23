package ua.waldemar.customdi.main.view.main.application.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.core.di.ScopeManager
import ua.waldemar.customdi.main.model.domain.UserInfoModel
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo
import ua.waldemar.customdi.main.view.main.MainActivity.Companion.MAIN_MODULE_KEY

data class HomeScreenState(
    val error: String? = null,
    val isLoading: Boolean = true,
    val userInfo: UserInfoModel? = null
)

class HomeViewModel(
    getUserInfoFlow: GetUserInfoFlow,
    private val refreshUserInfo: RefreshUserInfo
): ViewModel() {

    private val _screenState = MutableStateFlow(HomeScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        Log.d("LogLifecycle", "HomeViewModel created: $this")
        Log.d("LogLifecycle", "HomeViewModel@getUserInfoFlow: $getUserInfoFlow")
        Log.d("LogLifecycle", "HomeViewModel@refreshUserInfo: $refreshUserInfo")

        viewModelScope.launch {
            launch { refreshUserInfo() }
            launch {
                getUserInfoFlow().collect { result ->
                    result.onSuccess {
                        _screenState.emit(HomeScreenState(
                            isLoading = false,
                            userInfo = UserInfoModel(it.firstName, it.lastName, it.middleName)
                        ))
                    }.onFailure {
                        _screenState.emit(HomeScreenState(
                            isLoading = false,
                            error = it.message ?: "Unknown Error"
                        ))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        Log.d("LogLifecycle", "HomeViewModel cleared: $this")
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                with (ScopeManager.getScope(MAIN_MODULE_KEY)) {
                    HomeViewModel(
                        get(),
                        get()
                    )
                }
            }
        }
    }
}