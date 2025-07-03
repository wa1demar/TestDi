package ua.waldemar.customdi.main.view.di

import android.content.Context
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.model.di_v2.MainModelComponent

class MainViewComponent(
    context: Context,
    userId: String
) {

    private val model = MainModelComponent.create(context.applicationContext, userId)

    private val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            MainViewModelFactoryContributor(model.domainModule)
        )
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        contributors
            .flatMap { it.provide().entries }
            .associate { it.toPair() }
    }
}