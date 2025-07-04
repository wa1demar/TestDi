package ua.waldemar.customdi.main.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.waldemar.customdi.main.view.main.application.MainApp
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.di.MainDI
import ua.waldemar.customdi.main.view.di.MainViewComponent
import ua.waldemar.customdi.main.view.main.application.common.LocalViewModelFactoryProvider

class MainActivity : ComponentActivity() {

    private lateinit var component: MainViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialization
        val userId: String = intent.getStringExtra("userId") ?: run {
            finish()
            return
        }
        MainDI.init(applicationContext, userId)
        component = MainViewComponent()

        setContent {
            CustomDITheme {
                CompositionLocalProvider(
                    LocalViewModelFactoryProvider provides { modelClass ->
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return component.viewModelCreators.getValue(modelClass).invoke() as T
                            }
                        }
                    }
                ) {
                    MainApp()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MainDI.destroy()
    }

    companion object {
        fun launchIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("userId", userId)
            return intent
        }
    }
}