package ua.waldemar.customdi.main

import android.app.Application
import ua.waldemar.customdi.main.di.AppContainer

class App : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }
}