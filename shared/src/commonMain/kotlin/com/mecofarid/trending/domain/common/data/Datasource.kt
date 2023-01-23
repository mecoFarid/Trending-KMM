package com.mecofarid.trending.domain.common.data

interface Datasource<T> {
    suspend fun get(query: com.mecofarid.trending.domain.common.data.Query): T

    suspend fun put(query: com.mecofarid.trending.domain.common.data.Query, data: T): T
}
