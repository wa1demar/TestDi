package ua.waldemar.customdi.main.feature.updatepassword.presentation.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.feature.updatepassword.domain.UpdatePasswordUseCase
import ua.waldemar.customdi.main.feature.updatepassword.presentation.ui.UpdatePasswordFormViewModel

class UpdatePasswordFormViewModelFactoryContributor(
    private val useCase: UpdatePasswordUseCase
) : ViewModelFactoryContributor {
    override fun contribute(): Map<Class<out ViewModel>, () -> ViewModel> {
        return mapOf(
            UpdatePasswordFormViewModel::class.java to { UpdatePasswordFormViewModel(useCase) }
        )
    }
} 