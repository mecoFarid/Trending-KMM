package com.mecofarid.trending.domain.features.repo.data

import com.mecofarid.trending.domain.common.data.DataException.DataNotFoundException
import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.domain.features.repo.data.source.remote.entity.RepoRemoteEntity
import com.mecofarid.trending.domain.features.repo.domain.model.Trending

class TrendingRepository(
    private val cacheDatasource: com.mecofarid.trending.domain.common.data.Datasource<List<TrendingLocalEntity>>,
    private val mainDatasource: com.mecofarid.trending.domain.common.data.Datasource<List<RepoRemoteEntity>>,
    private val toLocalEntityMapper: com.mecofarid.trending.domain.common.data.Mapper<RepoRemoteEntity, TrendingLocalEntity>,
    private val toDomainMapper: com.mecofarid.trending.domain.common.data.Mapper<TrendingLocalEntity, Trending>
) : com.mecofarid.trending.domain.common.data.Repository<List<Trending>> {

    override suspend fun get(query: com.mecofarid.trending.domain.common.data.Query, operation: com.mecofarid.trending.domain.common.data.Operation): List<Trending> {
        return when (operation) {
            com.mecofarid.trending.domain.common.data.Operation.SyncMainOperation -> getSyncedData(query)
            com.mecofarid.trending.domain.common.data.Operation.CacheElseSyncMainOperation -> getCachedElseSyncedData(query)
        }
    }

    private suspend fun getSyncedData(query: com.mecofarid.trending.domain.common.data.Query) : List<Trending>{
        return try {
            val data = mainDatasource.get(query).map {
                toLocalEntityMapper.map(it)
            }
            val cachedData = cacheDatasource.put(query, data)
            cachedData.map { toDomainMapper.map(it) }
        } catch (ignored: DataNotFoundException) {
            getCachedData(query)
        }
    }

    private suspend fun getCachedElseSyncedData(query: com.mecofarid.trending.domain.common.data.Query): List<Trending>{
        return try {
            getCachedData(query)
        } catch (ignored: DataNotFoundException) {
            get(query, com.mecofarid.trending.domain.common.data.Operation.SyncMainOperation)
        }
    }

    private suspend fun getCachedData(query: com.mecofarid.trending.domain.common.data.Query): List<Trending> {
        val cachedData = cacheDatasource.get(query)
        return cachedData.map { toDomainMapper.map(it) }
    }
}
