package ua.waldemar.customdi.core.feature

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
inline fun <reified Holder : FeatureHolder<*>, reified VM : ViewModel> featureFlowViewModel(
    noinline holderFactory: (Context) -> Holder
): VM {
    return graphScopedViewModel(
        holderFactory = holderFactory,
        screenVmFactory = { holder ->
            val creator = (holder as Holder).component
                .let { (it as? HasViewModelCreators)?.viewModelCreators?.get(VM::class.java) }
                ?: throw IllegalStateException("Creator for ${VM::class.java.simpleName} not found")
            creator() as VM
        }
    )
}

interface HasViewModelCreators {
    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel>
} 