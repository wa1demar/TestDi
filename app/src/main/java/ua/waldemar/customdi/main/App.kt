package ua.waldemar.customdi.main

import android.app.Application
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import ua.waldemar.customdi.main.di.AppContainer

class App : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }
}

val LocalAppContainer: AppContainer
    @Composable
    get() = (
            requireNotNull(LocalActivity.current) { "LocalActivity is not available. Are you calling this outside of a Composition?" }
                .application as App
            ).appContainer