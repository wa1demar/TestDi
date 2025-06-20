package ua.waldemar.customdi.main.view.launch

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class UiLauncherObserver(
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {
    private val _resultEvent = MutableSharedFlow<ResultUiEvent>(replay = 1)
    val resultEvent: Flow<ResultUiEvent> = _resultEvent.asSharedFlow()

    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(owner: LifecycleOwner) {
        launcher = registry.register(
            UI_RESULT_REGISTER_KEY,
            owner,
            ActivityResultContracts.StartActivityForResult()
        ) { result -> _resultEvent.tryEmit(result.resultEvent) }
    }

    fun launchUi(intent: Intent) {
        launcher?.launch(intent)
    }

    private val ActivityResult.resultEvent: ResultUiEvent
        get() = takeIf { result -> result.resultCode == Activity.RESULT_OK }
            ?.data?.getStringExtra(RESULT_ACTION_KEY)
            ?.let { name -> enumValueOf<ResultUiEvent>(name) } ?: ResultUiEvent.END_SESSION

    companion object {
        const val RESULT_ACTION_KEY = "result_action_key"
        private const val UI_RESULT_REGISTER_KEY = "ui_result_register_key"
    }
}