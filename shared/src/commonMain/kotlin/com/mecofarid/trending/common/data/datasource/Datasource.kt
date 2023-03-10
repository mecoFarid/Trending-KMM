package com.mecofarid.trending.common.data.datasource

import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.either.Either

interface Datasource<T, E> {
    suspend fun get(query: Query): Either<E, T>

    suspend fun put(query: Query, data: T): Either<E, T>
}
