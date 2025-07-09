package ua.waldemar.customdi.core.di

import androidx.lifecycle.ViewModel

interface ViewModelFactoryContributor {
    fun contribute(): Map<Class<out ViewModel>, () -> ViewModel>
}