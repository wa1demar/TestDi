package ua.waldemar.customdi.main.view.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.model.di.MainModelComponent

class MainViewComponent() {

    private val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            MainViewModelFactoryContributor(MainModelComponent.get().domainModule)
        )
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        contributors
            .flatMap { it.provide().entries }
            .associate { it.toPair() }
    }
}