package ua.waldemar.customdi.di.init

import kotlinx.coroutines.flow.Flow

interface InitUiRepository {

    val initEvent: Flow<InitEvent>

    suspend fun initUi(data: String?)
}