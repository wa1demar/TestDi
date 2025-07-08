package ua.waldemar.customdi.main.feature.updatepassword.presentation.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.feature.FeatureHolder
import ua.waldemar.customdi.core.feature.featureFlowViewModel
import ua.waldemar.customdi.main.feature.updatepassword.di.UpdatePasswordComponent

class UpdatePasswordFeatureHolder(appContext: Context) : FeatureHolder<UpdatePasswordComponent>() {
    override val component = UpdatePasswordComponent()
}

@Composable
inline fun <reified VM : ViewModel> updatePasswordViewModel(): VM {
    return featureFlowViewModel(
        holderFactory = { context -> UpdatePasswordFeatureHolder(context) }
    )
}