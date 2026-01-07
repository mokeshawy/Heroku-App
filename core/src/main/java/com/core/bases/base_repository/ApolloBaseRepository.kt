package com.core.bases.base_repository

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.core.error.ApolloHttpStatusCodeException
import com.core.error.ApolloResponseException
import com.core.error.ApolloResponseNotFoundException
import com.core.error.ApolloResponseUnAuthorizedException
import com.core.error.AppError
import com.core.state.State
import java.io.IOException
import java.util.concurrent.CancellationException


private const val CODE = "code"

abstract class ApolloBaseRepository<RequestDto, ResponseDto> :
    BaseRepository<RequestDto, ResponseDto>() {


    override suspend fun getOperationState(requestDto: RequestDto): State<ResponseDto> {
        return try {
            performApiCall(requestDto)
        } catch (e: IOException) {
            State.Error(getIoExceptionError(e))
        } catch (e: ApolloHttpException) {
            State.Error(e.handleApolloHttpException())
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            State.Error(getGeneralExceptionError(e))
        }
    }

    abstract suspend fun performApiCall(requestDto: RequestDto): State<ResponseDto>


    private fun ApolloHttpException.handleApolloHttpException(): AppError = when (statusCode) {
        401 -> getUnauthorizedError(statusCode)
        404 -> getNotFoundError(statusCode)
        else -> getHttpError()
    }

    private fun getApolloResponseError(response: ApolloResponse<*>) = AppError.E(
        exception = ApolloResponseException(),
        logMessage = "Api request to url: ${response.errors}: failed with code ${response.extensions[CODE]}",
        extraData = response
    )


    private fun getUnauthorizedError(code: Int) = AppError.E(
        exception = ApolloResponseUnAuthorizedException(),
        logMessage = "Api request to url: failed with code $code"
    )

    private fun getNotFoundError(code: Int) = AppError.E(
        exception = ApolloResponseNotFoundException(),
        logMessage = "Api request to url: failed with code $code"
    )


    private fun getHttpError() = AppError.E(
        exception = ApolloHttpStatusCodeException(),
        logMessage = "Api request to url: failed with code ApolloHttpError"
    )


    fun <T> getNotSuccessfulResponseState(response: ApolloResponse<*>) =
        State.Error<T>(getApolloResponseError(response))

}