package ua.waldemar.customdi.presentation

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ua.waldemar.customdi.di.DomainProvider

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        DomainProvider.setup(applicationContext, applicationScope)
    }
}