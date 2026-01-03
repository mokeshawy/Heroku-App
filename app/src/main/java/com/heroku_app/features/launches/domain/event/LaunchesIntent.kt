package com.heroku_app.features.launches.domain.event

import com.core.bases.base_viewmodel.ViewIntent

sealed class LaunchesIntent : ViewIntent {
    data object LaunchesPagingIntent : LaunchesIntent()
}