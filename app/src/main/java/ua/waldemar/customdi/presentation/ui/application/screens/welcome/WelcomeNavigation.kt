package ua.waldemar.customdi.presentation.ui.application.screens.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class WelcomeScreenRoute(val reinit: Boolean)

fun NavController.navigateToWelcomeScreen(reinit: Boolean, navOptions: NavOptions? = null) =
    navigate(WelcomeScreenRoute(reinit), navOptions)

internal fun NavGraphBuilder.welcomeScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
) {
    composable<WelcomeScreenRoute> {
        WelcomeScreen(
            onLoginClicked = onLoginClicked,
            onRegisterClicked = onRegisterClicked,
        )
    }
}