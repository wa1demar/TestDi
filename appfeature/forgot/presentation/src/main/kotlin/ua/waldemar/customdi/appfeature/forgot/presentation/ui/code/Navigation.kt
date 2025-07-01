package ua.waldemar.customdi.appfeature.forgot.presentation.ui.code

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object ForgotPasswordCodeRoute

internal fun NavController.navigateToForgotPasswordCode(navOptions: NavOptions? = null) =
    navigate(ForgotPasswordCodeRoute, navOptions)

fun NavGraphBuilder.forgotPasswordCodeScreen() {
    composable<ForgotPasswordCodeRoute> { entry ->
        ForgotPasswordCodeScreen()
    }
}