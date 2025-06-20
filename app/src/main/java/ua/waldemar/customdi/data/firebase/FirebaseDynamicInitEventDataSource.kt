package ua.waldemar.customdi.data.firebase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import ua.waldemar.customdi.di.init.InitEvent
import ua.waldemar.customdi.main.view.AccessUI

class FirebaseDynamicInitEventDataSource( private val context: Context) {
    private val _initEvent = MutableSharedFlow<InitEvent>(replay = 1)
    val initEvent: Flow<InitEvent> = _initEvent.asSharedFlow()

    val unAuthInitEvent: Flow<Unit> = initEvent.filterIsInstance<InitEvent.UnAuth>().map {}

    suspend fun initUi(data: String?) {
        // check dynamic link and then
        AccessUI.setup(context, "key", true)
        _initEvent.emit(InitEvent.UnAuth)
    }
}