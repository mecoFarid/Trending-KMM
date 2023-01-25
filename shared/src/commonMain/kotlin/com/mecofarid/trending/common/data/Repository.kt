package com.mecofarid.trending.common.data

interface Repository<T> {
    suspend fun get(query: Query, operation: Operation): T
}
