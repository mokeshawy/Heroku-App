package com.heroku_app.features.launche_details.domain.event

import com.core.bases.base_viewmodel.ViewIntent

sealed class LaunchDetailsIntent : ViewIntent {
    data class LaunchById(val id: String) : LaunchDetailsIntent()
}