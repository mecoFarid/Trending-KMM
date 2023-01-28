package com.mecofarid.trending.features.trending.data

import TrendingResult
import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Datasource
import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.Repository
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.domain.model.Trending

class TrendingRepository(
    private val cacheDatasource: Datasource<List<TrendingLocalEntity>, DataException>,
    private val mainDatasource: Datasource<List<TrendingRemoteEntity>, DataException>,
    private val toLocalEntityMapper: Mapper<TrendingRemoteEntity, TrendingLocalEntity>,
    private val toDomainMapper: Mapper<TrendingLocalEntity, Trending>
) : Repository<List<Trending>, DataException> {

    override suspend fun get(query: Query, operation: Operation): TrendingResult<Trending> {
        return when (operation) {
            Operation.SyncMainOperation -> getSyncedData(query)
            Operation.CacheElseSyncMainOperation -> getCachedElseSyncedData(query)
        }
    }

    private suspend fun getSyncedData(query: Query): TrendingResult<Trending> =
        mainDatasource.get(query)
            .map {
                val data = it.map { trending ->
                    toLocalEntityMapper.map(trending)
                }
                cacheDatasource.put(query, data)
                return getCachedData(query)
            }
            .onLeft {
                return getCachedData(query)
            }


    private suspend fun getCachedElseSyncedData(query: Query): TrendingResult<Trending> =
        getCachedData(query)
            .onLeft {
                return get(query, Operation.SyncMainOperation)
            }

    private suspend fun getCachedData(query: Query): TrendingResult<Trending> {
        val cachedData = cacheDatasource.get(query)
        return cachedData.map {
            it.map { trending ->
                toDomainMapper.map(trending)
            }
        }
    }
}
