package ua.waldemar.customdi.main.feature.settings.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ua.waldemar.customdi.core.feature.DiScopeHost
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.settings.presentation.di.SettingsComponent
import ua.waldemar.customdi.main.feature.settings.presentation.ui.SettingsScreen

@Serializable
data object SettingsRoute

fun NavController.navigateToSettings(navOptions: NavOptions? = null) =
    navigate(SettingsRoute, navOptions)

fun NavGraphBuilder.settingsScreen(
    goToUpdatePassword: () -> Unit
) {
    composable<SettingsRoute> { entry ->
        val navController = LocalNavController.current
        val owner =  try { navController.getBackStackEntry(SettingsRoute) } catch (_: IllegalStateException) { null }
        DiScopeHost(
            componentFactory = { SettingsComponent() },
            viewModelCreatorsProvider = { it.viewModelCreators },
            owner = owner
        ) {
            SettingsScreen(goToUpdatePassword = goToUpdatePassword)
        }
    }
}