package ua.waldemar.customdi.main.view

import android.content.Context
import androidx.fragment.app.FragmentActivity
import ua.waldemar.customdi.main.model.di.MainModelComponent
import ua.waldemar.customdi.main.view.launch.UiLauncher

object AccessUI {

    suspend fun setup(context: Context, apiKey: String, allowPining: Boolean) {
        MainModelComponent.setUp(context, apiKey, allowPining)
    }

    fun getLauncher(activity: FragmentActivity): UiLauncher = UiLauncher(activity)
}