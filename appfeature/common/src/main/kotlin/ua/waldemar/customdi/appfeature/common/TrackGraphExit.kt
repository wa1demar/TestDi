package ua.waldemar.customdi.appfeature.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlin.reflect.KClass

@SuppressLint("RestrictedApi")
@Composable
fun <T : Any> TrackGraphExit(
    route: KClass<T>,
    onExit: () -> Unit
) {
    var wasInGraph by rememberSaveable { mutableStateOf(false) }
    val navController = LocalNavController.current

    LaunchedEffect(navController) {
        navController.currentBackStack.collect { newStack ->
            val isInGraph = newStack.map { it.destination }.any { it.hasRoute(route) }
            if (wasInGraph && !isInGraph) {
                Log.d("TrackGraphExit", "Exit from $route")
                onExit()
            }
            wasInGraph = isInGraph
        }
    }
}