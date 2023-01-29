package com.mecofarid.trending.features.trending

import com.mecofarid.trending.common.data.DatasourceMapper
import com.mecofarid.trending.common.data.ListMapper
import com.mecofarid.trending.common.data.VoidMapper
import com.mecofarid.trending.di.db.DbComponent
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.common.data.repository.cache.CacheRepository
import com.mecofarid.trending.features.trending.data.mapper.OwnerLocalEntityToOwnerMapper
import com.mecofarid.trending.features.trending.data.mapper.OwnerRemoteEntityToOwnerMapper
import com.mecofarid.trending.features.trending.data.mapper.OwnerToOwnerLocalEntityMapper
import com.mecofarid.trending.features.trending.data.mapper.TrendingLocalEntityToTrendingMapper
import com.mecofarid.trending.features.trending.data.mapper.TrendingRemoteEntityToTrendingMapper
import com.mecofarid.trending.features.trending.data.mapper.TrendingToTrendingLocalEntityMapper
import com.mecofarid.trending.features.trending.data.source.local.TrendingLocalDatasource
import com.mecofarid.trending.features.trending.data.source.remote.RepoRemoteDatasource
import com.mecofarid.trending.features.trending.domain.interactor.GetTrendingInteractor

interface TrendingComponent {
    fun getTrendingInteractor(): GetTrendingInteractor
}

class TrendingModule(
    private val dbComponent: DbComponent,
    private val networkComponent: NetworkComponent
): TrendingComponent {

    private val repository by lazy {
        val mainOutMapper =
            ListMapper(
                TrendingRemoteEntityToTrendingMapper(OwnerRemoteEntityToOwnerMapper())
            )
        val cacheOutMapper =
            ListMapper(TrendingLocalEntityToTrendingMapper(OwnerLocalEntityToOwnerMapper()))
        val cacheInMapper =
            ListMapper(TrendingToTrendingLocalEntityMapper(OwnerToOwnerLocalEntityMapper()))
        val mainDatasource = DatasourceMapper(
            RepoRemoteDatasource(networkComponent.trendingService()),
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
