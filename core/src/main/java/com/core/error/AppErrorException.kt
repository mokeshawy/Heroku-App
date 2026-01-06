package com.core.error

import androidx.annotation.Keep


open class AppErrorException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)

@Keep
class GeneralException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class IoException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class ResponseException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

@Keep
class ResponseUnAuthorizedException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


@Keep
class BadRequestException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)


@Keep
class ApolloResponseException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)



@Keep
class ApolloResponseUnAuthorizedException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)



@Keep
class ApolloResponseNotFoundException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)



@Keep
class ApolloHttpStatusCodeException(message: String? = null, cause: Throwable? = null) :
    AppErrorException(message, cause)

