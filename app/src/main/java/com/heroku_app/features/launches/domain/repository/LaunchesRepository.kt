package com.heroku_app.features.launches.domain.repository

import androidx.paging.PagingData
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    fun getLaunches(): Flow<PagingData<LaunchUiModel>>
}