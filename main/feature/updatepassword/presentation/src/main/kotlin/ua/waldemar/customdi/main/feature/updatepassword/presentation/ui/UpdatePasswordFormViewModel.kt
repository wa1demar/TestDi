package ua.waldemar.customdi.main.feature.updatepassword.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.waldemar.customdi.main.feature.updatepassword.domain.UpdatePasswordUseCase

class UpdatePasswordFormViewModel(
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel() {
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _success = MutableStateFlow<Boolean?>(null)
    val success = _success.asStateFlow()

    fun updatePassword(password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _error.value = "Passwords do not match"
            return
        }
        _error.value = null
        viewModelScope.launch {
            val result = updatePasswordUseCase(password)
            if (result) {
                _success.value = true
            } else {
                _error.value = "Failed to update password"
            }
        }
    }
} 