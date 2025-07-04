package ua.waldemar.customdi.main.view.main.application.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.main.model.domain.UserInfoModel
import ua.waldemar.customdi.main.model.domain.usecases.GetUserInfoFlow
import ua.waldemar.customdi.main.model.domain.usecases.RefreshUserInfo

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
}