package ua.waldemar.customdi.main.view

import android.content.Context
import androidx.fragment.app.FragmentActivity
import ua.waldemar.customdi.main.model.di_v2.AccessApiManager
import ua.waldemar.customdi.main.view.launch.UiLauncher

object AccessUI {

    fun setup(context: Context, apiKey: String, allowPining: Boolean) {
        AccessApiManager.setup(context, apiKey, allowPining)
    }

    fun getLauncher(activity: FragmentActivity): UiLauncher {
        return UiLauncher(activity)
    }
}