package ua.waldemar.customdi.core.feature

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun <C : Any> DiScopeHost(
    componentFactory: () -> C,
    onClear: ((C) -> Unit)? = null,
    viewModelCreatorsProvider: (C) -> Map<Class<out ViewModel>, () -> ViewModel>,
    owner: ViewModelStoreOwner? = null,
    content: @Composable () -> Unit
) {
    val component = if (owner != null) {
        val holder = viewModel<DiScopeHolder<C>>(viewModelStoreOwner = owner, factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DiScopeHolder(componentFactory()) as T
            }
        })
        holder.component
    } else {
        remember { componentFactory() }
    }

    DisposableEffect(Unit) {
        Log.d("DI_SCOPE", "${component::class.simpleName}[${component::class.hashCode()}] created. Component scope is created")
        onDispose {
            Log.d("DI_SCOPE", "${component::class.simpleName}[${component::class.hashCode()}] cleared. Component scope is destroyed.")
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

class DiScopeHolder<C : Any>(val component: C) : ViewModel()