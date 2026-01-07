package com.core.bases.base_repository


import com.core.error.AppError
import com.core.error.GeneralException
import com.core.error.IoException
import com.core.state.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.io.IOException


abstract class BaseRepository<RequestDto, ResponseDto> {

    var dispatcher: CoroutineDispatcher = Dispatchers.IO

    abstract suspend fun getOperationState(requestDto: RequestDto): State<ResponseDto>

    fun getIoExceptionError(e: IOException) = AppError.E(
        exception = IoException(cause = e),
        logMessage = "Failed to load data from Api with IOException",
    )

    fun getGeneralExceptionError(e: Exception) = AppError.E(
        exception = GeneralException(cause = e),
        logMessage = "Failed to load data from Api with General exception",
    )
}