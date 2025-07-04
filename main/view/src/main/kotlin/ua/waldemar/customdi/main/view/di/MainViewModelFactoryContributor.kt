package ua.waldemar.customdi.main.view.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.model.di.modules.DomainModule
import ua.waldemar.customdi.main.view.main.MainViewModel
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeViewModel

class MainViewModelFactoryContributor(
    private val domainModule: DomainModule
) : ViewModelFactoryContributor {
    override fun provide(): Map<Class<out ViewModel>, () -> ViewModel> {
        return mapOf(
            MainViewModel::class.java to { MainViewModel(domainModule.unexpectedErrorInteractor) },
            HomeViewModel::class.java to { HomeViewModel(domainModule.getUserInfoFlow, domainModule.refreshUserInfo) }
        )
    }
}