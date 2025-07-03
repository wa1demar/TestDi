package ua.waldemar.customdi.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ua.waldemar.customdi.appfeature.common.LocalComponentActivity
import ua.waldemar.customdi.appfeature.common.LocalNavController
import ua.waldemar.customdi.appfeature.forgot.presentation.forgotPasswordGraph
import ua.waldemar.customdi.appfeature.forgot.presentation.navigateToForgotPasswordGraph
import ua.waldemar.customdi.appfeature.common.LocalViewModelFactoryProvider
import ua.waldemar.customdi.appfeature.common.TrackGraphExit
import ua.waldemar.customdi.appfeature.forgot.presentation.ForgotPasswordGraph
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.email.ForgotPasswordEmailRoute
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.presentation.ui.common.createFactory
import ua.waldemar.customdi.presentation.ui.screens.signin.SignInScreenRoute
import ua.waldemar.customdi.presentation.ui.screens.signin.navigateToSignInScreen
import ua.waldemar.customdi.presentation.ui.screens.signin.signInScreen
import ua.waldemar.customdi.presentation.ui.screens.signup.SignUpScreenRoute
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
            LocalViewModelFactoryProvider provides { modelClass ->
                createFactory(appContainer, modelClass)
            },
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

            TrackGraphExit(ForgotPasswordGraph::class) {
                appContainer.clearForgotPasswordScope()
            }
        }
    }
}