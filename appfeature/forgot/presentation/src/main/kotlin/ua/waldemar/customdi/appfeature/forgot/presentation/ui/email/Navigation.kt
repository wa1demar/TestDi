package ua.waldemar.customdi.appfeature.forgot.presentation.ui.email

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ForgotPasswordEmailRoute

fun NavController.navigateToForgotPasswordEmail(navOptions: NavOptions? = null) =
    navigate(ForgotPasswordEmailRoute, navOptions)

fun NavGraphBuilder.forgotPasswordEmailScreen(
    navigateToCodeScreen: () -> Unit
) {
    composable<ForgotPasswordEmailRoute> { entry ->
        ForgotPasswordEmailScreen(navigateToCodeScreen = navigateToCodeScreen)
    }
}