package com.heroku_app.features.launches.domain.modle.state

import androidx.paging.PagingData
import com.core.bases.base_viewmodel.ViewState
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import kotlinx.coroutines.flow.Flow

data class LaunchesPagingState(
    val launchPagingUiModel: Flow<PagingData<LaunchUiModel>>? = null
) : ViewState
