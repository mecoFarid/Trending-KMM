package com.mecofarid.trending.features.trending.data.source.local

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.datasource.Datasource
import com.mecofarid.trending.common.either.Either
import com.mecofarid.trending.features.trending.data.TrendingResult
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingQuery
import com.mecofarid.trending.features.trending.data.source.local.dao.TrendingLocalEntityDao
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity

class TrendingLocalDatasource(
    private val trendingLocalEntityDao: TrendingLocalEntityDao
): Datasource<List<TrendingLocalEntity>, DataException> {
    override suspend fun get(query: Query): TrendingResult<TrendingLocalEntity> = when (query) {
        is GetAllTrendingQuery -> getAllTrending()
        else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
    }

    override suspend fun put(
        query: Query,
        data: List<TrendingLocalEntity>
    ): TrendingResult<TrendingLocalEntity> =
        when (query) {
            is GetAllTrendingQuery -> Either.Right(data).apply {
                trendingLocalEntityDao.deleteAllTrendingAndInsert(this.value)
            }
            else -> throw UnsupportedOperationException("Put with query type ($query) is not supported")
        }

    private fun getAllTrending() = with(trendingLocalEntityDao.getAllTrending()) {
        return@with if (isEmpty())
            Either.Left(DataException.DataNotFoundException())
        else
            Either.Right(this)
    }
}
