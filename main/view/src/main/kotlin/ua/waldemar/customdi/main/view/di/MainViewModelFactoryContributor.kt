package ua.waldemar.customdi.main.view.di

import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.main.model.di.modules.DomainModule
import ua.waldemar.customdi.main.view.main.MainViewModel
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeViewModel

class MainViewModelFactoryContributor(
    private val domainModule: DomainModule
) : ViewModelFactoryContributor {
    override fun provide() = viewModelFactories {
        factory<MainViewModel> { MainViewModel(domainModule.unexpectedErrorInteractor) }
        factory<HomeViewModel> { HomeViewModel(domainModule.getUserInfoFlow, domainModule.refreshUserInfo) }
    }
}