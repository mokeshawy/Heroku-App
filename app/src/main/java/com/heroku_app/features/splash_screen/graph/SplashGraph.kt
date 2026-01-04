package com.heroku_app.features.splash_screen.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heroku_app.features.launches.presentation.graph.navigateToLaunchesGraph
import com.heroku_app.features.splash_screen.presentation.SplashScreen
import kotlinx.serialization.Serializable


@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable<SplashScreen> {
        SplashScreen(onNavigateToOrderList = navController::navigateToLaunchesGraph)
    }
}