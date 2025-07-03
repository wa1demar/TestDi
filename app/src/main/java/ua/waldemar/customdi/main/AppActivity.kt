package ua.waldemar.customdi.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import ua.waldemar.customdi.main.view.AccessUI
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.core.theme.CustomDITheme

class AppActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels { AppViewModelFactory() }

    val ewaUiLauncher: UiLauncher by lazy { AccessUI.getLauncher(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomDITheme {
                MyApp(ewaUiLauncher)
            }
        }

        observeEwaResultEvent()

        // todo: show splash until setup
        viewModel.getRemoteConfig.receiveAsFlow()
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach {
                AccessUI.setup(this@AppActivity, "key", it)
            }.launchIn(lifecycleScope)
    }

    private fun observeEwaResultEvent() {
        ewaUiLauncher.resultUiEvent
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { event ->
//                when (event) {
//                    ResultUiEvent.LOG_OUT -> viewModel.onLogOut()
//                    ResultUiEvent.RELOGIN -> viewModel.onRelogin()
//                    ResultUiEvent.DUPLICATE_SESSION -> viewModel.onDuplicateSession()
//                    ResultUiEvent.MAINTENANCE_MODE -> viewModel.onMaintenanceMode()
//                    ResultUiEvent.ERROR -> viewModel.onEwaError()
//                    ResultUiEvent.CLOSE -> finish()
//                    ResultUiEvent.END_SESSION -> viewModel.onEndSession()
//                }
            }.launchIn(lifecycleScope)
    }
}
