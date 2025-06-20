package ua.waldemar.customdi.data.firebase

import kotlinx.coroutines.flow.Flow
import ua.waldemar.customdi.di.init.InitEvent
import ua.waldemar.customdi.di.init.InitUiRepository

class FirebaseInitUiRepository(
    private val dynamicLinkDataSource: FirebaseDynamicInitEventDataSource
) : InitUiRepository {
    override val initEvent: Flow<InitEvent> = dynamicLinkDataSource.initEvent

    override suspend fun initUi(data: String?) {
        dynamicLinkDataSource.initUi(data)
    }
}