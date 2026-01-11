package com.heroku_app.features.launche_details.domain.model.state

import com.core.bases.base_viewmodel.ViewState
import com.core.error.AppError
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel

data class LaunchDetailsState(
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val launchUiModel: LaunchUiModel? = null
) : ViewState