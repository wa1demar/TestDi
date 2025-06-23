package ua.waldemar.customdi.data.firebase

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ua.waldemar.customdi.di.init.InitUiRepository
import ua.waldemar.customdi.main.view.AccessUI

class InitUiRepositoryImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : InitUiRepository {

    override suspend fun initUi() {
        val certPinningEnabled = withContext(ioDispatcher) {
            delay(3000) // here is request to remote config
            true
        }
        AccessUI.setup(context, "BuildConfig.apiKey", certPinningEnabled)
    }
}