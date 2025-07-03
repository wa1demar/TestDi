package ua.waldemar.customdi.main.view.main.application.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

val LocalViewModelFactoryProvider = staticCompositionLocalOf<(Class<out ViewModel>) -> ViewModelProvider.Factory> {
    error("No ViewModelFactory provider found!")
}

@Composable
inline fun <reified VM : ViewModel> featureViewModel(): VM {
    val factoryProvider = LocalViewModelFactoryProvider.current
    val factory = factoryProvider(VM::class.java)
    return viewModel(factory = factory)
}