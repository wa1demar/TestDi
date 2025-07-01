package ua.waldemar.customdi.presentation.ui.common

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.AppActivity

typealias UserId = String

suspend fun Flow<UserId>.collectLaunchEwaUiEvent(
    activity: Activity,
) {
    collect { userId ->
        if (userId.isNotEmpty()) {
            (activity as? AppActivity)?.apply {
                ewaUiLauncher.launch(applicationContext, userId)
            }
        }
    }
}