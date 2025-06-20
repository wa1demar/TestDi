package ua.waldemar.customdi.main.view.main.application.common

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeViewModel

@Composable
internal inline fun <reified VM : ViewModel> mainViewModel() =
    viewModel<VM>(factory = createFactory<VM>())

@Composable
inline fun <reified T : ViewModel> activityViewModel(factory: ViewModelProvider.Factory): T {
    return viewModel(
        viewModelStoreOwner = LocalActivity.current as ComponentActivity,
        factory = factory
    )
}

internal inline fun <reified VM : ViewModel> createFactory(): ViewModelProvider.Factory? =
    when (VM::class) {
        HomeViewModel::class -> HomeViewModel.Factory
        else -> throw IllegalStateException("Failed to create instance of: ${VM::class}")
    }