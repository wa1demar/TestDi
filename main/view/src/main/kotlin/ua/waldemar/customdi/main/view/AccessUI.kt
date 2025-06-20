package ua.waldemar.customdi.main.view

import android.content.Context
import androidx.fragment.app.FragmentActivity
import ua.waldemar.customdi.main.model.di.MainModelComponent
import ua.waldemar.customdi.main.view.helpers.UiSignInHelper
import ua.waldemar.customdi.main.view.helpers.UiSignUpHelper
import ua.waldemar.customdi.main.view.helpers.mainHelper
import ua.waldemar.customdi.main.view.launch.UiLauncher

object AccessUI {

    val signInHelper: UiSignInHelper by mainHelper()

    val signUpHelper: UiSignUpHelper by mainHelper()

    suspend fun setup(context: Context, apiKey: String, allowPining: Boolean) {
        MainModelComponent.setUp(context, apiKey, allowPining)
    }

    fun getLauncher(activity: FragmentActivity): UiLauncher = UiLauncher(activity)
}