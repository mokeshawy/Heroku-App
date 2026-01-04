package com.heroku_app.features.launche_details.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heroku_app.features.common.viewmodel.MainViewModel
import com.heroku_app.features.launche_details.presentation.LaunchDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data object LaunchDetailsRouteScreen

fun NavGraphBuilder.launchDetailsGraph(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    composable<LaunchDetailsRouteScreen> {
        LaunchDetailsScreen(
            mainViewModel = mainViewModel,
            onBackClicked = navController::popBackStack
        )
    }
}

fun NavController.navigateToLaunchDetailsGraph() =
    navigate(route = LaunchDetailsRouteScreen)
