package ua.waldemar.customdi.di.init

import kotlinx.coroutines.flow.Flow

class InitUiInteractor(
    private val uiRepository: InitUiRepository,
) {
    val initEvent: Flow<InitEvent> = uiRepository.initEvent

    suspend fun initUi(data: String? = null) {
        uiRepository.initUi(data)
    }
}