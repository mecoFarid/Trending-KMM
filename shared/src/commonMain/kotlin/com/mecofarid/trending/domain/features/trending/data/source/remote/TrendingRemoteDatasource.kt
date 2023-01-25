package com.mecofarid.trending.domain.features.trending.data.source.remote

import com.mecofarid.trending.common.data.Datasource
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.domain.features.trending.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.domain.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.domain.features.trending.data.source.remote.service.TrendingService

const val TRENDING_REPOS_QUERY = "language=+sort:stars"

class RepoRemoteDatasource(
    private val trendingService: TrendingService
): Datasource<List<TrendingRemoteEntity>> {
    override suspend fun get(query: Query): List<TrendingRemoteEntity> =
        when (query) {
            GetAllTrendingReposQuery -> getTrendingRepos()
            else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
        }

    override suspend fun put(query: Query, data: List<TrendingRemoteEntity>): List<TrendingRemoteEntity> =
        throw UnsupportedOperationException("Put is not supported")

    private suspend fun getTrendingRepos(): List<TrendingRemoteEntity> =
        trendingService.searchRepos(TRENDING_REPOS_QUERY).items
}
