package ua.waldemar.customdi.main.feature.settings.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ua.waldemar.customdi.core.feature.DiScopeHost
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
        DiScopeHost(
            componentFactory = { SettingsComponent() },
            viewModelCreatorsProvider = { it.viewModelCreators }
        ) {
            SettingsScreen(goToUpdatePassword = goToUpdatePassword)
        }
    }
}