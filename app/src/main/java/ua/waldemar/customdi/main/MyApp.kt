package ua.waldemar.customdi.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ua.waldemar.customdi.appfeature.common.LocalComponentActivity
import ua.waldemar.customdi.appfeature.forgot.presentation.forgotPasswordGraph
import ua.waldemar.customdi.appfeature.forgot.presentation.navigateToForgotPasswordGraph
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.presentation.ui.screens.signin.navigateToSignInScreen
import ua.waldemar.customdi.presentation.ui.screens.signin.signInScreen
import ua.waldemar.customdi.presentation.ui.screens.signup.navigateToSignUpScreen
import ua.waldemar.customdi.presentation.ui.screens.signup.signUpScreen
import ua.waldemar.customdi.presentation.ui.screens.welcome.WelcomeScreenRoute
import ua.waldemar.customdi.presentation.ui.screens.welcome.welcomeScreen

@Composable
fun MyApp(
    ewaUiLauncher: UiLauncher,
    appViewModel: AppViewModel = viewModel(
        viewModelStoreOwner = LocalComponentActivity,
        factory = AppViewModelFactory()
    ),
) {
    val appContainer = LocalAppContainer
    CustomDITheme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
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
                signInScreen(
                    navigateToForgot = {
                        navController.navigateToForgotPasswordGraph("")
                    }
                )
                signUpScreen()

                forgotPasswordGraph()
            }
        }
    }
}