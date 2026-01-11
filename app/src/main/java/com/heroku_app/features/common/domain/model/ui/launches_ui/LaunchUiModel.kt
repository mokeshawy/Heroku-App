package com.heroku_app.features.common.domain.model.ui.launches_ui

data class LaunchUiModel(
    val id: String,
    val isBooked: Boolean,
    val missionUiModel: MissionUiModel?,
    val rocketUiModel: RocketUiModel?,
    val site: String?,
    var isFavorite: Boolean = false
)