package ua.waldemar.customdi.main.di

import android.content.Context
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.data.di.DataContainer
import ua.waldemar.customdi.main.di.modules.AuthUseCaseModule

class AppContainer(private val appContext: Context) {

    private val dataContainer by lazy { DataContainer(appContext) }

    private val authUseCases by lazy {
        with(dataContainer) {
            AuthUseCaseModule(
                signInRepository,
                signUpRepository,
            )
        }
    }

    private val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(AppViewModelFactoryContributor(authUseCases))
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel>
        get() =  contributors.flatMap { it.contribute().entries }.associate { it.toPair() }
}