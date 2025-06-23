package ua.waldemar.customdi.main.view.main.application

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ua.waldemar.customdi.core.di.Scope
import ua.waldemar.customdi.main.view.main.MainViewModel
import ua.waldemar.customdi.main.view.main.application.common.activityViewModel
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeScreenRoute
import ua.waldemar.customdi.main.view.main.application.screens.home.homeScreen
import ua.waldemar.customdi.main.view.main.application.screens.profile.navigateToProfileScreen
import ua.waldemar.customdi.main.view.main.application.screens.profile.profileScreen

@Composable
internal fun MainApp(
    mainDiScope: Scope,
    mainViewModel: MainViewModel = activityViewModel(factory = MainViewModel.Factory)
) {
    val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeScreenRoute,
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            modifier = Modifier.fillMaxSize()
        ) {
            homeScreen(
                onProfileClicked = {
                    navController.navigateToProfileScreen()
                }
            )
            profileScreen()
        }
}