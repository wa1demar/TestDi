package ua.waldemar.customdi.di.v2

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ua.waldemar.customdi.core.di.Scope

object AppQualifiers {
    object Dispatcher {
        const val IO = "dispatcherIO"
        const val Main = "dispatcherMain"
        const val Default = "dispatcherDefault"
    }
}
val ApplicationModule: Scope.Builder.(Context) -> Unit = { applicationContext ->
    single<Context> { applicationContext }
    single<CoroutineDispatcher>(AppQualifiers.Dispatcher.IO) { Dispatchers.IO }
    single<CoroutineDispatcher>(AppQualifiers.Dispatcher.Main) { Dispatchers.Main }
    single<CoroutineDispatcher>(AppQualifiers.Dispatcher.Default) { Dispatchers.Default }

    DataModule()
    DomainModule()
    ActivityModule()
}