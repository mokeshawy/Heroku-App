package com.heroku_app.features.launches.data.paging_data_sorce

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.heroku_app.LaunchesQuery
import com.heroku_app.features.launches.domain.mapper.toLaunchesUiModel
import com.heroku_app.features.common.domain.model.ui.launches_ui.LaunchUiModel
import javax.inject.Inject

class LaunchesPagingDataSource @Inject constructor(private val apolloClient: ApolloClient) :
    PagingSource<String, LaunchUiModel>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, LaunchUiModel> {

        return try {
            val response = apolloClient.query(
                LaunchesQuery(
                    pageSize = Optional.present(params.loadSize),
                    after = Optional.present(params.key)
                )
            ).execute()

            val data = response.data?.launches?.toLaunchesUiModel()

            LoadResult.Page(
                data = data?.launches.orEmpty(),
                prevKey = null,
                nextKey = if (data?.hasMore == true) data.cursor else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<String, LaunchUiModel>
    ): String? = null
}