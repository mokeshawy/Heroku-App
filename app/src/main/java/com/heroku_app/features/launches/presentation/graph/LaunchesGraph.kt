package com.heroku_app.features.launches.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heroku_app.features.launche_details.presentation.graph.navigateToLaunchDetailsGraph
import com.heroku_app.features.launches.presentation.LaunchesScreen
import com.heroku_app.nav_host.RootGraph
import kotlinx.serialization.Serializable


@Serializable
data object LaunchesScreen

fun NavGraphBuilder.launchesGraph(
    navController: NavController,
) {
    composable<LaunchesScreen> {
        LaunchesScreen(
            onNavigateToLaunchesDetailsScreen = navController::navigateToLaunchDetailsGraph
        )
    }
}

fun NavController.navigateToLaunchesGraph() {
    navigate(route = LaunchesScreen) {
        popUpTo(route = RootGraph) { inclusive = true }
    }
}