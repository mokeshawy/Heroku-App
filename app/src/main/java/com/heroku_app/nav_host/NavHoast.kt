package com.heroku_app.nav_host


import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.heroku_app.features.common.viewmodel.MainViewModel
import com.heroku_app.features.launche_details.presentation.graph.launchDetailsGraph
import com.heroku_app.features.launches.presentation.graph.launchesGraph
import com.heroku_app.features.splash_screen.graph.SplashScreen
import com.heroku_app.features.splash_screen.graph.splashGraph
import kotlinx.serialization.Serializable

@Serializable
data object RootGraph

@Composable
fun RootNavHost(viewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen::class,
        route = RootGraph::class
    ) {
        splashGraph(navController = navController)

        launchesGraph(
            navController = navController,
            mainViewModel = viewModel
        )

        launchDetailsGraph(
            navController = navController,
            mainViewModel = viewModel
        )

    }
}