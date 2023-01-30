package com.mecofarid.trending.common.data

import com.mecofarid.trending.common.data.datasource.Datasource
import com.mecofarid.trending.common.either.Either

class DatasourceMapper<I, O, E>(
    private val datasource: Datasource<I, E>,
    private val outMapper: Mapper<I, O>,
    private val inMapper: Mapper<O, I>
): Datasource<O, E> {

    override suspend fun get(query: Query): Either<E, O> {
        return datasource.get(query).map {
            outMapper.map(it)
        }
    }

    override suspend fun put(query: Query, data: O): Either<E, O> {
        return datasource.put(query, inMapper.map(data)).map {
            outMapper.map(it)
        }
    }
}