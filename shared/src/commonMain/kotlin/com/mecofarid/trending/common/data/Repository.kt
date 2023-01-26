package com.mecofarid.trending.common.data

import com.mecofarid.trending.common.either.Either

interface Repository<T, E> {
    suspend fun get(query: Query, operation: Operation): Either<E, T>
}
