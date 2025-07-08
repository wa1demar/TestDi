package ua.waldemar.customdi.core.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun <C : Any> DiScopeHost(
    componentFactory: () -> C,
    onClear: ((C) -> Unit)? = null,
    viewModelCreatorsProvider: (C) -> Map<Class<out ViewModel>, () -> ViewModel>,
    content: @Composable () -> Unit
) {
    val component = remember { componentFactory() }

    DisposableEffect(Unit) {
        onDispose {
            onClear?.invoke(component)
        }
    }

    CompositionLocalProvider(
        LocalViewModelFactoryProvider provides { clazz ->
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return viewModelCreatorsProvider(component)[modelClass]?.invoke() as? T
                        ?: error("No factory for $modelClass")
                }
            }
        }
    ) {
        content()
    }
}