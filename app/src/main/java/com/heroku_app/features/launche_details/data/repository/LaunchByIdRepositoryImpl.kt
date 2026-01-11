package com.heroku_app.features.launche_details.data.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.core.bases.base_repository.ApolloBaseRepository
import com.core.state.State
import com.heroku_app.LaunchByIdQuery
import com.heroku_app.features.launche_details.domain.data_source.LaunchByIdDataSource
import com.heroku_app.features.launche_details.domain.repository.LaunchByIdRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LaunchByIdRepositoryImpl @Inject constructor(private val launchByIdDataSource: LaunchByIdDataSource) :
    ApolloBaseRepository<String, LaunchByIdQuery.Launch>(), LaunchByIdRepository {


    override suspend fun getLaunchById(id: String) = flow {
        emit(value = getOperationState(requestDto = id))
    }.flowOn(context = dispatcher)


    override suspend fun performApiCall(requestDto: String) =
        launchByIdDataSource.getLaunchById(id = requestDto).handleLaunchByIdResponse()


    private fun ApolloResponse<LaunchByIdQuery.Data>.handleLaunchByIdResponse(): State<LaunchByIdQuery.Launch> {
        val data = data?.launch
        return when {
            !hasErrors() -> State.Success(data)
            else -> getNotSuccessfulResponseState(response = this)
        }
    }
}