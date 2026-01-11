package com.heroku_app.features.launche_details.data.data_source

import com.apollographql.apollo3.ApolloClient
import com.heroku_app.LaunchByIdQuery
import com.heroku_app.features.launche_details.domain.data_source.LaunchByIdDataSource
import javax.inject.Inject

class LaunchByIdDataSourceImpl @Inject constructor(private val apolloClient: ApolloClient) :
    LaunchByIdDataSource {

    override suspend fun getLaunchById(id: String) =
        apolloClient.query(LaunchByIdQuery(id = id)).execute()

}