package com.heroku_app.features.launches.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.heroku_app.features.launches.data.paging_data_sorce.LaunchesPagingDataSource
import com.heroku_app.features.launches.domain.modle.ui.LaunchUiModel
import com.heroku_app.features.launches.domain.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LaunchesRepository {

    override fun getLaunches(): Flow<PagingData<LaunchUiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            ), pagingSourceFactory = {
                LaunchesPagingDataSource(apolloClient)
            }).flow
    }
}