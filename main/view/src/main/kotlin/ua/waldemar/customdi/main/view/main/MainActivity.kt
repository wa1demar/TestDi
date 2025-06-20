package ua.waldemar.customdi.main.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ua.waldemar.customdi.main.view.main.application.MainApp
import ua.waldemar.customdi.core.theme.CustomDITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomDITheme {
                MainApp()
            }
        }
    }

    companion object {
        fun launchIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}