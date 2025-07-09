package ua.waldemar.customdi.main.feature.withdraw.presentation.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.feature.FeatureHolder
import ua.waldemar.customdi.core.feature.featureFlowViewModel

class WithdrawFeatureHolder(appContext: Context) : FeatureHolder<WithdrawComponent>() {
    override val component = WithdrawComponent()
}

@Composable
inline fun <reified VM : ViewModel> withdrawViewModel(): VM {
    return featureFlowViewModel { context -> WithdrawFeatureHolder(context) }
}