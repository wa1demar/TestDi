package ua.waldemar.customdi.main.feature.withdraw.presentation.di

import ua.waldemar.customdi.core.di.FeatureComponent
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.core.di.viewModelFactories
import ua.waldemar.customdi.core.feature.HasViewModelCreators
import ua.waldemar.customdi.main.feature.withdraw.data.di.WithdrawDataContainer
import ua.waldemar.customdi.main.feature.withdraw.domain.WithdrawUseCase
import ua.waldemar.customdi.main.feature.withdraw.presentation.WithdrawDataHolder
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step1.WithdrawFormStep1ViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step2.WithdrawFormStep2ViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step3.WithdrawFormConfirmViewModel
import ua.waldemar.customdi.main.feature.withdraw.presentation.ui.step4.WithdrawResultViewModel

class WithdrawComponent : FeatureComponent(), HasViewModelCreators {
    val dataHolder = WithdrawDataHolder()

    private val dataContainer = WithdrawDataContainer()

    private val useCase = WithdrawUseCase(dataContainer.withdrawRepository)

    override val contributors = listOf(
        object : ViewModelFactoryContributor {
            override fun provide() = viewModelFactories {
                factory<WithdrawFormStep1ViewModel> { WithdrawFormStep1ViewModel(dataHolder) }
            }
        },
        object : ViewModelFactoryContributor {
            override fun provide() = viewModelFactories {
                factory<WithdrawFormStep2ViewModel> { WithdrawFormStep2ViewModel(dataHolder) }
            }
        },
        object : ViewModelFactoryContributor {
            override fun provide() = viewModelFactories {
                factory<WithdrawFormConfirmViewModel> { WithdrawFormConfirmViewModel(dataHolder, useCase) }
            }
        },
        object : ViewModelFactoryContributor {
            override fun provide() = viewModelFactories {
                factory<WithdrawResultViewModel> { WithdrawResultViewModel() }
            }
        },
    )
}