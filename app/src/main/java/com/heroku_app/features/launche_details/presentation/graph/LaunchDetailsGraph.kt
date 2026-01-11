package com.heroku_app.features.launche_details.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.heroku_app.features.launche_details.presentation.LaunchDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class LaunchDetailsRouteScreen(val id : String, val missionName : String?)

fun NavGraphBuilder.launchDetailsGraph(navController: NavController) {
    composable<LaunchDetailsRouteScreen> {
        LaunchDetailsScreen(onBackClicked = navController::popBackStack)
    }
}

fun NavController.navigateToLaunchDetailsGraph(id: String, missionName: String?) =
    navigate(route = LaunchDetailsRouteScreen(id = id, missionName = missionName))
