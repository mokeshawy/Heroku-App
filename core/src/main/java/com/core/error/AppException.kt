package com.core.error

data class AppException(val appError: AppError) : Exception()