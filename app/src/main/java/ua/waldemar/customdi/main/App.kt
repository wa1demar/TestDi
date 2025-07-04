package ua.waldemar.customdi.main

import android.app.Application
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import ua.waldemar.customdi.main.di.AppContainer

class App : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
        Log.d("AppComponent", "AppContainer scope created")
    }
}

val LocalAppContainer: AppContainer
    @Composable
    get() = (
            requireNotNull(LocalActivity.current) { "LocalActivity is not available. Are you calling this outside of a Composition?" }
                .application as App
            ).appContainer