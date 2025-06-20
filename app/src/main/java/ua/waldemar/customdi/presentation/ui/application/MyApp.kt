package ua.waldemar.customdi.presentation.ui.application

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.take
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.di.init.InitEvent
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.presentation.AppViewModel
import ua.waldemar.customdi.presentation.ui.application.screens.signin.navigateToSignInScreen
import ua.waldemar.customdi.presentation.ui.application.screens.signin.signInScreen
import ua.waldemar.customdi.presentation.ui.application.screens.signup.navigateToSignUpScreen
import ua.waldemar.customdi.presentation.ui.application.screens.signup.signUpScreen
import ua.waldemar.customdi.presentation.ui.application.screens.welcome.WelcomeScreenRoute
import ua.waldemar.customdi.presentation.ui.application.screens.welcome.welcomeScreen

@Composable
fun MyApp(
    ewaUiLauncher: UiLauncher,
    appViewModel: AppViewModel = viewModel(
        viewModelStoreOwner = LocalActivity.current as ComponentActivity,
        factory = AppViewModel.Factory
    ),
    context: Context = LocalContext.current
) {
    CustomDITheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = WelcomeScreenRoute(false),
        ) {
            welcomeScreen(
                onLoginClicked = {
                    navController.navigateToSignInScreen()
                },
                onRegisterClicked = {
                    navController.navigateToSignUpScreen()
                }
            )
            signInScreen()
            signUpScreen()
        }

        LaunchedEffect(Unit) {
            appViewModel.initEvent.take(1)
                .collect { event ->
                    when (event) {
                        is InitEvent.Auth -> {
                            ewaUiLauncher.launch(context, event.token)
                        }

                        InitEvent.UnAuth -> navController.apply {
                            // show error
                        }
                    }
                }
        }
    }
}