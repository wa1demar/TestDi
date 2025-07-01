package ua.waldemar.customdi.appfeature.forgot.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.code.forgotPasswordCodeScreen
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.email.ForgotPasswordEmailRoute
import ua.waldemar.customdi.appfeature.forgot.presentation.ui.email.forgotPasswordEmailScreen

@Serializable
internal data class ForgotPasswordGraph(val predefinedEmail: String?)

fun NavController.navigateToForgotPasswordGraph(predefinedEmail: String?, navOptions: NavOptions? = null) =
    navigate(ForgotPasswordGraph(predefinedEmail), navOptions)

fun NavGraphBuilder.forgotPasswordGraph(
    navigateToCodeScreen: () -> Unit
) {
    navigation<ForgotPasswordGraph>(
        startDestination = ForgotPasswordEmailRoute
    ) {
        forgotPasswordEmailScreen(
            navigateToCodeScreen = navigateToCodeScreen
        )
        forgotPasswordCodeScreen()
    }
}
