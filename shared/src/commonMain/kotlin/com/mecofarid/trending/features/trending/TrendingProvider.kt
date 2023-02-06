package com.mecofarid.trending.features.trending

import com.mecofarid.trending.common.data.*
import com.mecofarid.trending.common.data.datasource.network.NetworkDatasource
import com.mecofarid.trending.common.data.repository.cache.CacheRepository
import com.mecofarid.trending.di.db.DbComponent
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.mapper.*
import com.mecofarid.trending.features.trending.data.source.local.TrendingLocalDatasource
import com.mecofarid.trending.features.trending.domain.interactor.GetTrendingInteractor

interface TrendingComponent {
    fun getTrendingInteractor(): GetTrendingInteractor
}

class TrendingModule(
    private val dbComponent: DbComponent,
    private val networkComponent: NetworkComponent
): TrendingComponent {

    private val repository by lazy {
        val networkExceptionMapper = object : Mapper<NetworkException, DataException> {
            override fun map(input: NetworkException) = DataException.DataNotFoundException(input)
        }

        val mainOutMapper =
            ListMapper(
                TrendingRemoteEntityToTrendingMapper(OwnerRemoteEntityToOwnerMapper())
            )
        val cacheOutMapper =
            ListMapper(TrendingLocalEntityToTrendingMapper(OwnerLocalEntityToOwnerMapper()))
        val cacheInMapper =
            ListMapper(TrendingToTrendingLocalEntityMapper(OwnerToOwnerLocalEntityMapper()))
        val mainDatasource = DatasourceMapper(
            NetworkDatasource(networkComponent.trendingService(), networkExceptionMapper),
            mainOutMapper,
            VoidMapper()
        )
        val cacheDataSource = DatasourceMapper(
            TrendingLocalDatasource(dbComponent.trendingLocalEntityDao()),
            cacheOutMapper,
            cacheInMapper
        )

        CacheRepository(cacheDataSource, mainDatasource)
    }

    override fun getTrendingInteractor(): GetTrendingInteractor = GetTrendingInteractor(repository)
}
