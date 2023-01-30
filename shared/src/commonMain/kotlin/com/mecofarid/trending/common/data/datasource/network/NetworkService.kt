package com.mecofarid.trending.common.data.datasource.network

import com.mecofarid.trending.common.data.Query

interface NetworkService<T> {
    suspend fun get(query: Query): T
    suspend fun put(query: Query, data: T): T
}