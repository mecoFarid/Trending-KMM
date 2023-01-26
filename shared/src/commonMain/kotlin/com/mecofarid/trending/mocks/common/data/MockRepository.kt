package com.mecofarid.trending.mocks.common.data

import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.Repository
import com.mecofarid.trending.common.either.Either

class MockRepository<T, E>(private var result: () -> Either<E, T>): Repository<T, E> {
    override suspend fun get(query: Query, operation: Operation): Either<E, T> = result()
}