package com.mecofarid.trending.domain.features.repo.data.source.remote

import com.mecofarid.trending.domain.features.repo.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.domain.features.repo.data.source.remote.entity.RepoRemoteEntity
import com.mecofarid.trending.domain.features.repo.data.source.remote.service.TrendingService

const val TRENDING_REPOS_QUERY = "language=+sort:stars"

class RepoRemoteDatasource(
    private val trendingService: TrendingService
): com.mecofarid.trending.domain.common.data.Datasource<List<RepoRemoteEntity>> {
    override suspend fun get(query: com.mecofarid.trending.domain.common.data.Query): List<RepoRemoteEntity> =
        when (query) {
            GetAllTrendingReposQuery -> getTrendingRepos()
            else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
        }

    override suspend fun put(query: com.mecofarid.trending.domain.common.data.Query, data: List<RepoRemoteEntity>): List<RepoRemoteEntity> =
        throw UnsupportedOperationException("Put is not supported")

    private suspend fun getTrendingRepos(): List<RepoRemoteEntity> =
        trendingService.searchRepos(TRENDING_REPOS_QUERY).items
}
