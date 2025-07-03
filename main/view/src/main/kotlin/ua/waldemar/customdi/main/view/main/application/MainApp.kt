package ua.waldemar.customdi.main.view.main.application

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ua.waldemar.customdi.main.view.AccessUI
import ua.waldemar.customdi.main.view.main.application.common.LocalViewModelFactoryProvider
import ua.waldemar.customdi.main.view.main.application.screens.home.HomeScreenRoute
import ua.waldemar.customdi.main.view.main.application.screens.home.homeScreen

@Composable
internal fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        modifier = Modifier.fillMaxSize()
    ) {
        homeScreen()
    }
}