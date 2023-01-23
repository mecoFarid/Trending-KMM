package com.mecofarid.trending.domain.common.data

sealed class NetworkException(override val cause: Throwable? = null): Throwable(cause) {
    data class HttpException(val code: Int): com.mecofarid.trending.domain.common.data.NetworkException()
    data class ConnectionException(override val cause: Throwable? = null): com.mecofarid.trending.domain.common.data.NetworkException(cause)
}
