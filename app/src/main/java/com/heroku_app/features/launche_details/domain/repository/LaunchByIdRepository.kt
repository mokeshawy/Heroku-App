package com.heroku_app.features.launche_details.domain.repository

import com.core.state.State
import com.heroku_app.LaunchByIdQuery
import kotlinx.coroutines.flow.Flow

interface LaunchByIdRepository {

    suspend fun getLaunchById(id: String): Flow<State<LaunchByIdQuery.Launch>>

}