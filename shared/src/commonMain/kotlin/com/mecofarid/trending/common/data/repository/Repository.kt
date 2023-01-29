package com.mecofarid.trending.common.data.repository

import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.either.Either

interface Repository<T, E> {
    suspend fun get(query: Query, operation: Operation): Either<E, T>
}
