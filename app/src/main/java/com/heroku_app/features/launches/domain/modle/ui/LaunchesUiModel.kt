package com.heroku_app.features.launches.domain.modle.ui

data class LaunchesUiModel(
    val cursor: String,
    val hasMore: Boolean,
    val launches: List<LaunchUiModel>,
)
