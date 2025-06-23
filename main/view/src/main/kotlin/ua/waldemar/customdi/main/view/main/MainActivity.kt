package ua.waldemar.customdi.main.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.core.di.ScopeManager
import ua.waldemar.customdi.main.view.main.application.MainApp
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.di.LocalScope
import ua.waldemar.customdi.main.view.di.MainModule

class MainActivity : ComponentActivity() {
    private lateinit var mainDiScope: Scope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDiScope = ScopeManager.createScope(MAIN_MODULE_KEY, MainModule)

        setContent {
            CustomDITheme {
                MainApp(mainDiScope)
            }
        }
    }

    override fun onDestroy() {
        if (isFinishing) {
            ScopeManager.destroyScope(MAIN_MODULE_KEY)
        }
        super.onDestroy()
    }

    companion object {
        internal val MAIN_MODULE_KEY = "main"
        fun launchIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}