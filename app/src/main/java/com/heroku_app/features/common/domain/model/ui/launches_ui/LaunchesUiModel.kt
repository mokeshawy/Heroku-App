package com.heroku_app.features.common.domain.model.ui.launches_ui

data class LaunchesUiModel(
    val cursor: String,
    val hasMore: Boolean,
    val launches: List<LaunchUiModel>,
)
