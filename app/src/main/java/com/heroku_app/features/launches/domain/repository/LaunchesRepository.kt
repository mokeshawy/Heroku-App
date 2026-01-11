package com.heroku_app.features.launches.domain.repository

import androidx.paging.PagingData
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LaunchesRepository {

    fun getLaunches(): Flow<PagingData<LaunchUiModel>>

    fun getFavorites(): StateFlow<List<LaunchUiModel>>

    fun toggleFavorite(launch: LaunchUiModel)

}