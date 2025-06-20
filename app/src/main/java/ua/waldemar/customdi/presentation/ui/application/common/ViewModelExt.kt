package ua.waldemar.customdi.presentation.ui.application.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.waldemar.customdi.presentation.ui.application.screens.signin.SignInViewModel
import ua.waldemar.customdi.presentation.ui.application.screens.signup.SignUpViewModel

@Composable
internal inline fun <reified VM : ViewModel> mainViewModel() =
    viewModel<VM>(
        factory = createFactory<VM>()
    )

internal inline fun <reified VM : ViewModel> createFactory(): ViewModelProvider.Factory? =
    when (VM::class) {
        SignInViewModel::class -> SignInViewModel.Factory
        SignUpViewModel::class -> SignUpViewModel.Factory
        else -> throw IllegalStateException("Failed to create instance of: ${VM::class}")
    }
