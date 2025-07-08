package ua.waldemar.customdi.main.feature.updatepassword.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable
import ua.waldemar.customdi.main.feature.updatepassword.presentation.ui.UpdatePasswordFormScreen
import ua.waldemar.customdi.main.feature.updatepassword.presentation.ui.UpdatePasswordResultScreen

@Serializable
data object UpdatePasswordGraph {
    @Serializable
    data object UpdatePasswordFormRoute
    @Serializable
    data object UpdatePasswordResultRoute
}

fun NavController.navigateToUpdatePasswordGraph(navOptions: NavOptions? = null) =
    navigate(UpdatePasswordGraph, navOptions)

fun NavController.navigateToUpdatePasswordResult(navOptions: NavOptions? = null) =
    navigate(UpdatePasswordGraph.UpdatePasswordResultRoute, navOptions)


fun NavGraphBuilder.updatePasswordGraph(
    onBackToSettings: () -> Unit
) {
    navigation<UpdatePasswordGraph>(
        startDestination = UpdatePasswordGraph.UpdatePasswordFormRoute,
    ) {
        composable<UpdatePasswordGraph.UpdatePasswordFormRoute> {
            UpdatePasswordFormScreen()
        }
        composable<UpdatePasswordGraph.UpdatePasswordResultRoute> {
            UpdatePasswordResultScreen(onBackToSettings = onBackToSettings)
        }
    }
}