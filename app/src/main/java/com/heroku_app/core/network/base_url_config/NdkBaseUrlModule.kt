package com.heroku_app.core.network.base_url_config

object NdkBaseUrlModule {

    private const val BASE_URL_LIB = "base-url-lib"
    init {
        System.loadLibrary(BASE_URL_LIB)
    }

    external fun getHerokuAppBaseUrl(): String

}