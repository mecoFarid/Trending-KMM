package com.mecofarid.trending.features.trending

import com.mecofarid.trending.di.db.DbComponent
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.TrendingRepository
import com.mecofarid.trending.features.trending.data.mapper.OwnerLocalEntityToOwnerMapper
import com.mecofarid.trending.features.trending.data.mapper.OwnerRemoteEntityToOwnerLocalEntityMapper
import com.mecofarid.trending.features.trending.data.mapper.RepoLocalEntityToRepoMapper
import com.mecofarid.trending.features.trending.data.mapper.RepoRemoteEntityToLocalEntityMapper
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
        val cacheDataSource = TrendingLocalDatasource(dbComponent.trendingLocalEntityDao())
        val mainDatasource = RepoRemoteDatasource(networkComponent.trendingService())

        val toLocalEntityMapper = RepoRemoteEntityToLocalEntityMapper(
            OwnerRemoteEntityToOwnerLocalEntityMapper()
        )
        val toDomainMapper = RepoLocalEntityToRepoMapper(OwnerLocalEntityToOwnerMapper())

        TrendingRepository(
            cacheDataSource,
            mainDatasource,
            toLocalEntityMapper,
            toDomainMapper
        )
    }

    override fun getTrendingInteractor(): GetTrendingInteractor = GetTrendingInteractor(repository)
}
