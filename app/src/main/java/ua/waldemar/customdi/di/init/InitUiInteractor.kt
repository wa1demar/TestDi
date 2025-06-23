package ua.waldemar.customdi.di.init

class InitUiInteractor(
    private val uiRepository: InitUiRepository,
) {
    suspend fun initUi() {
        uiRepository.initUi()
    }
}