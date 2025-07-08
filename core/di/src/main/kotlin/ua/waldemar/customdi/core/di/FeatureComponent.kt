package ua.waldemar.customdi.core.di

import androidx.lifecycle.ViewModel

abstract class FeatureComponent {

    protected abstract val contributors: List<ViewModelFactoryContributor>

    val viewModelCreators: Map<Class<out ViewModel>, () -> ViewModel> by lazy {
        contributors.flatMap { it.provide().entries }.associate { it.toPair() }
    }
}