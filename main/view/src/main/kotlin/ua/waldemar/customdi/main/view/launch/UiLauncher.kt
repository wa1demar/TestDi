package ua.waldemar.customdi.main.view.launch

import android.content.Context
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.main.view.main.MainActivity

class UiLauncher internal constructor(activity: FragmentActivity) {
    private val launcherObserver: UiLauncherObserver

    init {
        activity.apply {
            launcherObserver = UiLauncherObserver(activityResultRegistry)
            lifecycle.addObserver(launcherObserver)
        }
    }

    val resultUiEvent: Flow<ResultUiEvent> = launcherObserver.resultEvent

    fun launch(context: Context, userId: String) {
        launcherObserver.launchUi(MainActivity.launchIntent(context))
    }
}