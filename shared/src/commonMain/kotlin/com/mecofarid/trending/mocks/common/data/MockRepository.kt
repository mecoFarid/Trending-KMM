package com.mecofarid.trending.mocks.common.data

import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.Repository

class MockRepository<T>(private var result: () -> T): Repository<T> {
    override suspend fun get(query: Query, operation: Operation): T {
        return result()
    }
}