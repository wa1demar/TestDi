package ua.waldemar.customdi.main.view.main.application.screens.profile

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
import ua.waldemar.customdi.main.model.di.MainModelComponent
import ua.waldemar.customdi.main.model.domain.UserInfoModel
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo
import ua.waldemar.customdi.main.view.di.LocalScope
import ua.waldemar.customdi.main.view.main.MainActivity.Companion.MAIN_MODULE_KEY

data class ProfileScreenState(
    val error: String? = null,
    val isLoading: Boolean = true,
    val userInfo: UserInfoModel? = null
)

class ProfileViewModel(
    getUserInfoFlow: GetUserInfoFlow,
    private val refreshUserInfo: RefreshUserInfo
): ViewModel() {

    private val _screenState = MutableStateFlow(ProfileScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        Log.d("LogLifecycle", "ProfileViewModel created: $this")
        Log.d("LogLifecycle", "ProfileViewModel@getUserInfoFlow: $getUserInfoFlow")
        Log.d("LogLifecycle", "ProfileViewModel@refreshUserInfo: $refreshUserInfo")

        viewModelScope.launch {
            launch { refreshUserInfo() }
            launch {
                getUserInfoFlow().collect { result ->
                    result.onSuccess {
                        _screenState.emit(ProfileScreenState(
                            isLoading = false,
                            userInfo = UserInfoModel(it.firstName, it.lastName, it.middleName)
                        ))
                    }.onFailure {
                        _screenState.emit(ProfileScreenState(
                            isLoading = false,
                            error = it.message ?: "Unknown Error"
                        ))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        Log.d("LogLifecycle", "ProfileViewModel cleared: $this")
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                with (ScopeManager.getScope(MAIN_MODULE_KEY)) {
                    ProfileViewModel(
                        get(),
                        get()
                    )
                }
            }
        }
    }
}