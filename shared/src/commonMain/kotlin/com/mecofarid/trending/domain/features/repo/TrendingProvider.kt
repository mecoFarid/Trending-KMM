package com.mecofarid.trending.domain.features.repo

import com.mecofarid.trending.domain.di.db.DbComponent
import com.mecofarid.trending.domain.di.network.NetworkComponent
import com.mecofarid.trending.domain.features.repo.data.TrendingRepository
import com.mecofarid.trending.domain.features.repo.data.mapper.OwnerLocalEntityToOwnerMapper
import com.mecofarid.trending.domain.features.repo.data.mapper.OwnerRemoteEntityToOwnerLocalEntityMapper
import com.mecofarid.trending.domain.features.repo.data.mapper.RepoLocalEntityToRepoMapper
import com.mecofarid.trending.domain.features.repo.data.mapper.RepoRemoteEntityToLocalEntityMapper
import com.mecofarid.trending.domain.features.repo.data.source.local.TrendingLocalDatasource
import com.mecofarid.trending.domain.features.repo.data.source.remote.RepoRemoteDatasource
import com.mecofarid.trending.domain.features.repo.domain.interactor.GetTrendingInteractor

interface RepoComponent {
    fun getRepoInteractor(): GetTrendingInteractor
}

class RepoModule(
    private val dbComponent: DbComponent,
    private val networkComponent: NetworkComponent
): RepoComponent {

    private val repository by lazy {
        val cacheDataSource = TrendingLocalDatasource(dbComponent.repoLocalEntityDao())
        val mainDatasource = RepoRemoteDatasource(networkComponent.repoService())

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

    override fun getRepoInteractor(): GetTrendingInteractor = GetTrendingInteractor(repository)
}
