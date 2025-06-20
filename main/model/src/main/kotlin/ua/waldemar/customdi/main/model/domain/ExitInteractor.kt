package ua.waldemar.customdi.main.model.domain

import ua.waldemar.customdi.main.model.di.ModuleScope

class ExitInteractor internal constructor(
    private val moduleScope: ModuleScope
) {

    fun exit() {
        moduleScope.close()
    }
}