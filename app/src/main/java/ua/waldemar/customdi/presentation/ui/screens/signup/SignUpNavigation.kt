package ua.waldemar.customdi.presentation.ui.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object SignUpScreenRoute

fun NavController.navigateToSignUpScreen(
    navOptions: NavOptions? = null
) = navigate(
    SignUpScreenRoute,
    navOptions
)

internal fun NavGraphBuilder.signUpScreen() {
    composable<SignUpScreenRoute> { entry ->
        SignUpScreen()
    }
}