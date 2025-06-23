package ua.waldemar.customdi.presentation

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.core.di.ScopeManager
import ua.waldemar.customdi.di.v2.ApplicationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

//        val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
//        DomainProvider.setup(applicationContext, coroutineScope)

        val applicationScope = CoroutineScope(Dispatchers.Main)
        ScopeManager.createScope("app") {
            single { applicationScope }
            ApplicationModule(this@App)
        }
    }
}