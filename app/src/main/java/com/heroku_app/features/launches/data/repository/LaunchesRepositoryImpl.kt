package com.heroku_app.features.launches.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import com.heroku_app.features.launches.data.paging_data_sorce.LaunchesPagingDataSource
import com.heroku_app.features.launches.domain.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LaunchesRepository {

    private val favoriteList = MutableStateFlow<List<LaunchUiModel>>(value = emptyList())

    override fun getLaunches(): Flow<PagingData<LaunchUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            ), pagingSourceFactory = {
                LaunchesPagingDataSource(apolloClient)
            }).flow
    }

    override fun getFavorites(): StateFlow<List<LaunchUiModel>> = favoriteList

    override fun toggleFavorite(launch: LaunchUiModel) {
        val current = favoriteList.value.toMutableList()
        if (current.any { it.id == launch.id }) {
            current.removeAll { it.id == launch.id }
        } else {
            current.add(launch.copy(isFavorite = true))
        }
        favoriteList.value = current
    }
}
