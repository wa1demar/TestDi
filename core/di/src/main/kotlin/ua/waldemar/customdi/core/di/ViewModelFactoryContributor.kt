package ua.waldemar.customdi.core.di

import androidx.lifecycle.ViewModel

interface ViewModelFactoryContributor {
    fun provide(): Map<Class<out ViewModel>, () -> ViewModel>
}