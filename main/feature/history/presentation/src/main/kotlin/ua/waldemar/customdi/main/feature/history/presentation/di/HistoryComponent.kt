package ua.waldemar.customdi.main.feature.history.presentation.di

import ua.waldemar.customdi.core.di.FeatureComponent
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.feature.history.data.di.HistoryDataContainer

class HistoryComponent : FeatureComponent() {
    private val dataContainer = HistoryDataContainer()

    private val useCases = HistoryUseCaseModule(
        dataContainer.historyRepository
    )

    override val contributors: List<ViewModelFactoryContributor> by lazy {
        listOf(HistoryViewModelFactoryContributor(useCases.getHistoryUseCase))
    }
}