package ua.waldemar.customdi.main.feature.history.presentation.di

import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.main.feature.history.domain.GetHistoryUseCase
import ua.waldemar.customdi.main.feature.history.presentation.ui.HistoryViewModel

class HistoryViewModelFactoryContributor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModelFactoryContributor {
    override fun contribute() = viewModelFactories {
        factory<HistoryViewModel> { HistoryViewModel(getHistoryUseCase) }
    }
}