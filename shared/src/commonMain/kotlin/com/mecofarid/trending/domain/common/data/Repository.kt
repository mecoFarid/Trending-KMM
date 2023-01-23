package com.mecofarid.trending.domain.common.data

interface Repository<T> {
    suspend fun get(query: com.mecofarid.trending.domain.common.data.Query, operation: com.mecofarid.trending.domain.common.data.Operation): T
}
