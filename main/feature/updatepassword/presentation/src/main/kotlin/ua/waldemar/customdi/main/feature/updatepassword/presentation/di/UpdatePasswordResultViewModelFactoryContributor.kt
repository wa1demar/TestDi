package ua.waldemar.customdi.main.feature.updatepassword.presentation.di

import androidx.lifecycle.ViewModel
import ua.waldemar.customdi.core.di.ViewModelFactoryContributor
import ua.waldemar.customdi.main.feature.updatepassword.presentation.ui.UpdatePasswordResultViewModel

class UpdatePasswordResultViewModelFactoryContributor : ViewModelFactoryContributor {
    override fun contribute(): Map<Class<out ViewModel>, () -> ViewModel> {
        return mapOf(
            UpdatePasswordResultViewModel::class.java to { UpdatePasswordResultViewModel() }
        )
    }
} 