package com.heroku_app.nav_host


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import com.heroku_app.features.splash_screen.graph.SplashScreen
import com.heroku_app.features.splash_screen.graph.splashGraph

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
    }
}