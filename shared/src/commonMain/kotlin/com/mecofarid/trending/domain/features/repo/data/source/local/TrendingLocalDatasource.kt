package com.mecofarid.trending.domain.features.repo.data.source.local

import com.mecofarid.trending.domain.features.repo.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.domain.features.repo.data.source.local.dao.TrendingLocalEntityDao
import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity

class TrendingLocalDatasource(
    private val trendingLocalEntityDao: TrendingLocalEntityDao
): com.mecofarid.trending.domain.common.data.Datasource<List<TrendingLocalEntity>> {
    override suspend fun get(query: com.mecofarid.trending.domain.common.data.Query): List<TrendingLocalEntity> = when (query) {
        GetAllTrendingReposQuery -> getReposOrThrow()
        else -> throw UnsupportedOperationException("Get with query type ($query) is not supported")
    }

    override suspend fun put(query: com.mecofarid.trending.domain.common.data.Query, data: List<TrendingLocalEntity>): List<TrendingLocalEntity> =
        when (query) {
            GetAllTrendingReposQuery -> data.apply {
                trendingLocalEntityDao.deleteAllTrendingReposAndInsert(this)
            }
            else -> throw UnsupportedOperationException("Put with query type ($query) is not supported")
        }

    private suspend fun getReposOrThrow() = trendingLocalEntityDao.getAllTrendingRepos().also {
        if (it.isEmpty())
            throw com.mecofarid.trending.domain.common.data.DataException.DataNotFoundException()
    }
}
