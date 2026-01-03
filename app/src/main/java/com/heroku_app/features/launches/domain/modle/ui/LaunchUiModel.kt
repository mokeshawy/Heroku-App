package com.heroku_app.features.launches.domain.modle.ui

data class LaunchUiModel(
    val id: String,
    val isBooked: Boolean,
    val missionUiModel: MissionUiModel?,
    val rocketUiModel: RocketUiModel?,
    val site: String?
)