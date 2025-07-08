package ua.waldemar.customdi.main.feature.history.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ua.waldemar.customdi.main.feature.history.presentation.ui.HistoryScreen

@Serializable
data object HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions? = null) =
    navigate(HistoryRoute, navOptions)

fun NavGraphBuilder.historyScreen() {
    composable<HistoryRoute> { entry ->
        HistoryScreen()
    }
}