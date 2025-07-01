package ua.waldemar.customdi.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ua.waldemar.customdi.appfeature.forgot.presentation.forgotPasswordGraph
import ua.waldemar.customdi.appfeature.forgot.presentation.navigateToForgotPasswordGraph
import ua.waldemar.customdi.appfeature.common.LocalViewModelFactoryProvider
import ua.waldemar.customdi.core.theme.CustomDITheme
import ua.waldemar.customdi.main.view.launch.UiLauncher
import ua.waldemar.customdi.presentation.ui.common.createFactory
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
        viewModelStoreOwner = LocalActivity.current as ComponentActivity,
        factory = AppViewModelFactory()
    ),
) {
    val appContainer = (LocalActivity.current!!.application as App).appContainer
    CustomDITheme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalViewModelFactoryProvider provides { modelClass ->
                createFactory(appContainer, modelClass)
            }
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

                forgotPasswordGraph(
                    navigateToCodeScreen = {
                        /// ???
                    }
                )
            }
        }

        LaunchedEffect(Unit) {
//            appViewModel.initEvent.take(1)
//                .collect { event ->
//                    when (event) {
//                        is InitEvent.Auth -> {
//                            ewaUiLauncher.launch(context, event.token)
//                        }
//
//                        InitEvent.UnAuth -> navController.apply {
//                            // show error
//                        }
//                    }
//                }
        }
    }
}