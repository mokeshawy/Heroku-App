package com.heroku_app.nav_host


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.heroku_app.features.launche_details.presentation.graph.launchDetailsGraph
import com.heroku_app.features.launches.presentation.graph.launchesGraph
import com.heroku_app.features.splash_screen.graph.SplashScreen
import com.heroku_app.features.splash_screen.graph.splashGraph
import kotlinx.serialization.Serializable

@Serializable
data object RootGraph

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen::class,
        route = RootGraph::class
    ) {
        splashGraph(navController = navController)

        launchesGraph(navController = navController)

        launchDetailsGraph(navController = navController)

    }
}