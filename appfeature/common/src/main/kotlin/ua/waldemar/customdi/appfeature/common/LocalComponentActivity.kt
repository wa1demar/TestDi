package ua.waldemar.customdi.appfeature.common

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable

val LocalComponentActivity: ComponentActivity
    @Composable
    get() = requireNotNull(LocalActivity.current as? ComponentActivity) {
        "LocalActivity is not a ComponentActivity"
    }