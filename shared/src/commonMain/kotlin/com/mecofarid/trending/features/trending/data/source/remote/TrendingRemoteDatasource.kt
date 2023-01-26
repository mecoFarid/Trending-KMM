package com.mecofarid.trending.features.trending.data.source.remote

import TrendingResult
import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Datasource
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.either.Either
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService

const val TRENDING_REPOS_QUERY = "language=+sort:stars"

class RepoRemoteDatasource(
    private val trendingService: TrendingService
): Datasource<List<TrendingRemoteEntity>, DataException> {
    override suspend fun get(query: Query): TrendingResult<TrendingRemoteEntity> =
        when (query) {
            GetAllTrendingReposQuery -> getTrendingRepos()
            else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
        }

    override suspend fun put(query: Query, data: List<TrendingRemoteEntity>): TrendingResult<TrendingRemoteEntity> =
        throw UnsupportedOperationException("Put is not supported")

    // TODO: Change call adapter to return Either instead of throwing exception. Once done remove try catch
    private suspend fun getTrendingRepos(): TrendingResult<TrendingRemoteEntity> =
        try {
            Either.Right(trendingService.searchRepos(TRENDING_REPOS_QUERY).items)
        }catch (e: DataException.DataNotFoundException){
            Either.Left(e)
        }

}
