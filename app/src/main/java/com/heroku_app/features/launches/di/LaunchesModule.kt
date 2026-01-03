package com.heroku_app.features.launches.di

import com.apollographql.apollo3.ApolloClient
import com.heroku_app.features.launches.data.repository.LaunchesRepositoryImpl
import com.heroku_app.features.launches.domain.repository.LaunchesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object LaunchesModule {

    @Provides
    fun providesLaunchesRepositoryImpl(apolloClient: ApolloClient): LaunchesRepository =
        LaunchesRepositoryImpl(apolloClient = apolloClient)
}