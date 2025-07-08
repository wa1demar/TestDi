package ua.waldemar.customdi.appfeature.forgot.presentation.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.appfeature.forgot.presentation.di.ForgotPasswordComponent
import ua.waldemar.customdi.core.feature.graphScopedViewModel

class ForgotPasswordFeatureHolder(appContext: Context) : ViewModel() {

    val component = ForgotPasswordComponent(appContext)

    init {
        Log.d("DI_SCOPE", "ForgotPasswordFeatureHolder created.")
    }

    override fun onCleared() {
        Log.d("DI_SCOPE", "ForgotPasswordFeatureHolder cleared. Component scope is destroyed.")
        super.onCleared()
    }
}

@Composable
inline fun <reified VM : ViewModel> forgotPasswordViewModel(): VM {
    return graphScopedViewModel(
        holderFactory = { context -> ForgotPasswordFeatureHolder(context) },
        screenVmFactory = { holder ->
            val creator = holder.component.viewModelCreators[VM::class.java]
                ?: throw IllegalStateException("Creator for ${VM::class.java.name} not found")
            creator() as VM
        }
    )
}