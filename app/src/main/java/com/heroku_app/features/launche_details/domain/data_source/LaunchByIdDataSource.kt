package com.heroku_app.features.launche_details.domain.data_source

import com.apollographql.apollo3.api.ApolloResponse
import com.heroku_app.LaunchByIdQuery

interface LaunchByIdDataSource {

    suspend fun getLaunchById(id: String): ApolloResponse<LaunchByIdQuery.Data>
}