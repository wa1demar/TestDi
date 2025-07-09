package ua.waldemar.customdi.main.feature.withdraw.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
data object WithdrawGraph {
    @Serializable
    data object WithdrawFormStep1Route
    @Serializable
    data object WithdrawFormStep2Route
    @Serializable
    data object WithdrawFormConfirmRoute
    @Serializable
    data object WithdrawResultRoute
}

fun NavController.navigateToStep2(navOptions: NavOptions? = null) =
    navigate(WithdrawGraph.WithdrawFormStep2Route, navOptions)

fun NavController.navigateToConfirm(navOptions: NavOptions? = null) =
    navigate(WithdrawGraph.WithdrawFormConfirmRoute, navOptions)

fun NavController.navigateToResult(navOptions: NavOptions? = null) =
    navigate(WithdrawGraph.WithdrawResultRoute, navOptions)


fun NavGraphBuilder.withdrawGraph() {
    navigation<WithdrawGraph>(
        startDestination = WithdrawGraph.WithdrawFormStep1Route,
    ) {
        composable<WithdrawGraph.WithdrawFormStep1Route> {
            WithdrawFormStep1Screen()
        }
        composable<WithdrawGraph.WithdrawFormStep2Route> {
            WithdrawFormStep2Screen()
        }
        composable<WithdrawGraph.WithdrawFormConfirmRoute> {
            WithdrawFormConfirmScreen()
        }
        composable<WithdrawGraph.WithdrawResultRoute> {
            WithdrawResultScreen()
        }
    }
} 