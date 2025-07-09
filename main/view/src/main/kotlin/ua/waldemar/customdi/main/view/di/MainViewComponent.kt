package ua.waldemar.customdi.main.view.di

import android.content.Context
import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.feature.history.presentation.di.HistoryComponent
import ua.waldemar.customdi.main.model.di.MainModelComponent

class MainViewComponent(context: Context) {

    private val featureComponents = listOf(HistoryComponent())

    private val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(
            MainViewModelFactoryContributor(MainModelComponent.get().domainModule)
        ) + featureComponents.flatMap { it.contributors }
    }

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        contributors.flatMap { it.contribute().entries }.associate { it.toPair() }
    }
}