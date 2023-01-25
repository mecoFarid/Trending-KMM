package com.mecofarid.trending.domain.features.trending.data

import com.mecofarid.trending.common.data.DataException.DataNotFoundException
import com.mecofarid.trending.common.data.Datasource
import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.Repository
import com.mecofarid.trending.domain.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.domain.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.domain.features.trending.domain.model.Trending

class TrendingRepository(
    private val cacheDatasource: Datasource<List<TrendingLocalEntity>>,
    private val mainDatasource: Datasource<List<TrendingRemoteEntity>>,
    private val toLocalEntityMapper: Mapper<TrendingRemoteEntity, TrendingLocalEntity>,
    private val toDomainMapper: Mapper<TrendingLocalEntity, Trending>
) : Repository<List<Trending>> {

    override suspend fun get(query: Query, operation: Operation): List<Trending> {
        return when (operation) {
            Operation.SyncMainOperation -> getSyncedData(query)
            Operation.CacheElseSyncMainOperation -> getCachedElseSyncedData(query)
        }
    }

    private suspend fun getSyncedData(query: Query) : List<Trending>{
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

    private suspend fun getCachedElseSyncedData(query: Query): List<Trending>{
        return try {
            getCachedData(query)
        } catch (ignored: DataNotFoundException) {
            get(query, Operation.SyncMainOperation)
        }
    }

    private suspend fun getCachedData(query: Query): List<Trending> {
        val cachedData = cacheDatasource.get(query)
        return cachedData.map { toDomainMapper.map(it) }
    }
}
