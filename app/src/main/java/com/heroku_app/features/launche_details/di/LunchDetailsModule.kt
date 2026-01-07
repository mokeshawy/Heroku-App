package com.heroku_app.features.launche_details.di

import com.apollographql.apollo3.ApolloClient
import com.heroku_app.features.launche_details.data.data_source.LaunchByIdDataSourceImpl
import com.heroku_app.features.launche_details.data.repository.LaunchByIdRepositoryImpl
import com.heroku_app.features.launche_details.domain.data_source.LaunchByIdDataSource
import com.heroku_app.features.launche_details.domain.repository.LaunchByIdRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LunchDetailsModule {


    @Provides
    fun providesLaunchByIdDataSourceImpl(apolloClient: ApolloClient): LaunchByIdDataSource =
        LaunchByIdDataSourceImpl(apolloClient = apolloClient)

    @Provides
    fun providesLaunchByIdRepositoryImpl(launchByIdDataSource: LaunchByIdDataSource): LaunchByIdRepository =
        LaunchByIdRepositoryImpl(launchByIdDataSource = launchByIdDataSource)

}