package ua.waldemar.customdi.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ua.waldemar.customdi.core.di.ScopeManager
import ua.waldemar.customdi.main.view.AccessUI
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.presentation.ui.application.MyApp
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.di.AppViewModelFactory

class AppActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels {
        AppViewModel.Factory
    }

    val ewaUiLauncher: UiLauncher by lazy { AccessUI.getLauncher(this) }

    private var showSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition { showSplashScreen }
        setContent {
            MyApp(ewaUiLauncher)
        }

        observeInitializationEvent()
        observeEwaResultEvent()
    }

    private fun observeInitializationEvent() {
        viewModel.isInitInProgress
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach {
                showSplashScreen = it
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
