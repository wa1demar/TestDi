package ua.waldemar.customdi.main.di.modules

import ua.waldemar.customdi.di.init.InitUiInteractor
import ua.waldemar.customdi.di.init.InitUiRepository

class InitUseCaseModule(
    private val initUiRepository: InitUiRepository
) {
    val initUiInteractor: InitUiInteractor
        get() = InitUiInteractor(initUiRepository)
}