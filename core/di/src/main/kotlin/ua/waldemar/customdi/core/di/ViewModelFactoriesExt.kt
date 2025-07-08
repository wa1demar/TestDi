package ua.waldemar.customdi.core.di

import androidx.lifecycle.ViewModel

inline fun viewModelFactories(block: ViewModelFactoryDsl.() -> Unit): Map<Class<out ViewModel>, () -> ViewModel> {
    val dsl = ViewModelFactoryDsl()
    dsl.block()
    return dsl.factories
}

class ViewModelFactoryDsl {
    val factories = mutableMapOf<Class<out ViewModel>, () -> ViewModel>()

    inline fun <reified VM : ViewModel> factory(noinline provider: () -> VM) {
        factories[VM::class.java] = provider
    }
}