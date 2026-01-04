package com.heroku_app.core.network.apollo_client

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.heroku_app.core.network.base_url_config.NdkBaseUrlModule
import com.pluto.plugins.network.interceptors.okhttp.PlutoOkhttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloClientModule {


    @Provides
    @Singleton
    fun httpLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Timber.tag("OkHttp").d(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(NetLoggingInterceptor())
        .addInterceptor(PlutoOkhttpInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideApolloClient(
        okHttpClient: OkHttpClient
    ) = ApolloClient.Builder()
        .serverUrl(NdkBaseUrlModule.getHerokuAppBaseUrl())
        .okHttpClient(okHttpClient)
        .build()

}


