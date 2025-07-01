package ua.waldemar.customdi.appfeature.forgot.presentation.ui.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ForgotPasswordEmailViewModelFactory(
    private val creator: () -> ForgotPasswordEmailViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}