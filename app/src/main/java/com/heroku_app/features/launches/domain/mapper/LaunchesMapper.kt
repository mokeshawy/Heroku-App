package com.heroku_app.features.launches.domain.mapper

import com.heroku_app.LaunchesQuery
import com.heroku_app.LaunchesQuery.Launch

import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchesUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.MissionUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.RocketUiModel


fun LaunchesQuery.Launches.toLaunchesUiModel() = LaunchesUiModel(
    cursor = cursor,
    hasMore = hasMore,
    launches = launches.mapNotNull { it?.toLaunchUiModel()}
)

fun Launch.toLaunchUiModel() = LaunchUiModel(
    id = id,
    isBooked = isBooked,
    missionUiModel = mission?.toMissionUiModel(),
    rocketUiModel = rocket?.toRocketUiModel(),
    site = site
)

fun LaunchesQuery.Mission.toMissionUiModel() = MissionUiModel(
    missionPatch = missionPatch,
    name = name
)

fun LaunchesQuery.Rocket.toRocketUiModel() = RocketUiModel(
    name = name,
    type = type
)

