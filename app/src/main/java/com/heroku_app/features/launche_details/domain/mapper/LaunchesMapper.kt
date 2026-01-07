package com.heroku_app.features.launche_details.domain.mapper

import com.heroku_app.LaunchByIdQuery
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.MissionUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.RocketUiModel


fun LaunchByIdQuery.Launch.toLaunchUiModel() = LaunchUiModel(
    id = id,
    isBooked = isBooked,
    missionUiModel = mission?.toMissionUiModel(),
    rocketUiModel = rocket?.toRocketUiModel(),
    site = site
)

fun LaunchByIdQuery.Mission.toMissionUiModel() = MissionUiModel(
    missionPatch = missionPatch,
    name = name
)

fun LaunchByIdQuery.Rocket.toRocketUiModel() = RocketUiModel(
    name = name,
    type = type
)

