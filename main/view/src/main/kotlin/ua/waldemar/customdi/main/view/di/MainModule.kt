package ua.waldemar.customdi.main.view.di

import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.main.model.di.v2.MainDomainModule

val MainModule: Scope.Builder.() -> Unit = {
    MainDomainModule()
}