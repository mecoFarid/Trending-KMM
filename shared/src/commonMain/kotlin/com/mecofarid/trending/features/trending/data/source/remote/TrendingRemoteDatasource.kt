package com.mecofarid.trending.features.trending.data.source.remote

import TrendingResult
import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Datasource
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingQuery
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService

class RepoRemoteDatasource(
    private val trendingService: TrendingService
): Datasource<List<TrendingRemoteEntity>, DataException> {
    override suspend fun get(query: Query): TrendingResult<TrendingRemoteEntity> =
        when (query) {
            is GetAllTrendingQuery -> query.getTrendingRepos()
            else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
        }

    override suspend fun put(query: Query, data: List<TrendingRemoteEntity>): TrendingResult<TrendingRemoteEntity> =
        throw UnsupportedOperationException("Put is not supported")

    private suspend fun GetAllTrendingQuery.getTrendingRepos(): TrendingResult<TrendingRemoteEntity> =
        trendingService.searchRepos(this).map {
            it.items
        }
}
