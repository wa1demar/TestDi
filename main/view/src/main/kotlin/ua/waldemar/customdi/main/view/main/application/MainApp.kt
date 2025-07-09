package ua.waldemar.customdi.main.view.main.application

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ua.waldemar.customdi.core.feature.LocalNavController
import ua.waldemar.customdi.main.feature.history.presentation.HistoryRoute
import ua.waldemar.customdi.main.feature.history.presentation.historyScreen
import ua.waldemar.customdi.main.feature.history.presentation.navigateToHistory
import ua.waldemar.customdi.main.feature.settings.presentation.navigateToSettings
import ua.waldemar.customdi.main.feature.settings.presentation.settingsScreen
import ua.waldemar.customdi.main.feature.updatepassword.presentation.UpdatePasswordGraph
import ua.waldemar.customdi.main.feature.updatepassword.presentation.navigateToUpdatePasswordGraph
import ua.waldemar.customdi.main.feature.updatepassword.presentation.updatePasswordGraph
import ua.waldemar.customdi.main.feature.withdraw.presentation.WithdrawGraph
import ua.waldemar.customdi.main.feature.withdraw.presentation.withdrawGraph
import ua.waldemar.customdi.main.view.di.MainViewComponent
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeScreenRoute
import ua.waldemar.customdi.main.view.main.application.screens.home.homeScreen

@Composable
internal fun MainApp(component: MainViewComponent) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomBar(navController)
        }
    ) { innerPaddings ->
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeScreenRoute,
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPaddings)
            ) {
                homeScreen(
                    onHistoryClicked = {
                        navController.navigateToHistory()
                    },
                    onSettingsClicked = {
                        navController.navigateToSettings()
                    }
                )
                historyScreen()
                settingsScreen(
                    goToUpdatePassword = {
                        navController.navigateToUpdatePasswordGraph()
                    }
                )
                updatePasswordGraph(
                    onBackToSettings = {
                        navController.navigateToSettings(navOptions {
                            popUpTo(UpdatePasswordGraph) { inclusive = true }
                            launchSingleTop = true
                        })
                    }
                )
                withdrawGraph()
            }
        }
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppNavigationItem(
            screen = MainScreenTabs.Home,
            navController
        )
        AppNavigationItem(
            screen = MainScreenTabs.Withdraw,
            navController
        )
        AppNavigationItem(
            screen = MainScreenTabs.History,
            navController
        )
    }
}

@Composable
fun RowScope.AppNavigationItem(
    screen: MainScreenTabs,
    navController: NavController
) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            if (screen == MainScreenTabs.Withdraw) {
                navController.navigate(route = screen.route, navOptions {
                    popUpTo(WithdrawGraph) {
                        inclusive = false
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                })
            } else {
                navController.navigate(route = screen.route)
            }
        },
        modifier = Modifier.weight(1f)
    ) {
        Column {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = screen.title,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

sealed class MainScreenTabs(val route: Any, val title: String, val icon: Int) {
    data object Home : MainScreenTabs(
        HomeScreenRoute,
        "Home",
        android.R.drawable.btn_star_big_on
    )
    data object Withdraw : MainScreenTabs(
        WithdrawGraph,
        "Withdraw",
        android.R.drawable.ic_menu_preferences
    )
    data object History : MainScreenTabs(
        HistoryRoute,
        "History",
        android.R.drawable.btn_star_big_off
    )
}
