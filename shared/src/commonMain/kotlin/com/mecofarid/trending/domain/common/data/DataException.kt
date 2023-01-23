package com.mecofarid.trending.domain.common.data

sealed class DataException(override val cause: Throwable? = null): Throwable(cause) {
    data class DataNotFoundException(override val cause: Throwable? = null): com.mecofarid.trending.domain.common.data.DataException(cause)
}
