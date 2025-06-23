package ua.waldemar.customdi.main.view.main.application.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object ProfileScreenRoute

internal fun NavController.navigateToProfileScreen(navOptions: NavOptions? = null) =
    navigate(ProfileScreenRoute, navOptions)

internal fun NavGraphBuilder.profileScreen() {
    composable<ProfileScreenRoute> {
        ProfileScreen()
    }
}