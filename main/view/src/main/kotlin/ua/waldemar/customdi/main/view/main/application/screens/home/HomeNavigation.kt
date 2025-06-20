package ua.waldemar.customdi.main.view.main.application.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object HomeScreenRoute

internal fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) =
    navigate(HomeScreenRoute, navOptions)

internal fun NavGraphBuilder.homeScreen() {
    composable<HomeScreenRoute> {
        HomeScreen()
    }
}