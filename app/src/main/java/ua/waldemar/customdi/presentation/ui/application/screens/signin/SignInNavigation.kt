package ua.waldemar.customdi.presentation.ui.application.screens.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class SignInScreenRoute(
    val appLaunchedFromNotifications: Boolean,
    val defaultEmail: String? = null,
    val resetPasswordFailed: Boolean = false,
)

fun NavController.navigateToSignInScreen(
    appLaunchedFromNotifications: Boolean = false,
    defaultEmail: String? = null,
    resetPasswordFailed: Boolean = false,
    navOptions: NavOptions? = null
) = navigate(
    SignInScreenRoute(
        appLaunchedFromNotifications = appLaunchedFromNotifications,
        defaultEmail = defaultEmail,
        resetPasswordFailed = resetPasswordFailed
    ),
    navOptions
)

internal fun NavGraphBuilder.signInScreen() {
    composable<SignInScreenRoute> { entry ->
        SignInScreen()
    }
}