package com.mecofarid.trending.domain.features.repo.data.mapper

import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity.OwnerLocalEntity
import com.mecofarid.trending.domain.features.repo.data.source.remote.entity.RepoRemoteEntity
import com.mecofarid.trending.domain.features.repo.data.source.remote.entity.RepoRemoteEntity.OwnerRemoteEntity

class RepoRemoteEntityToLocalEntityMapper(
    private val ownerMapper: com.mecofarid.trending.domain.common.data.Mapper<OwnerRemoteEntity, OwnerLocalEntity>
): com.mecofarid.trending.domain.common.data.Mapper<RepoRemoteEntity, TrendingLocalEntity> {

    override fun map(input: RepoRemoteEntity): TrendingLocalEntity =
        with(input){
            TrendingLocalEntity(
                name,
                language,
                stargazersCount,
                description,
                ownerMapper.map(owner)
            )
        }
}

class OwnerRemoteEntityToOwnerLocalEntityMapper:
    com.mecofarid.trending.domain.common.data.Mapper<OwnerRemoteEntity, OwnerLocalEntity> {

    override fun map(input: OwnerRemoteEntity): OwnerLocalEntity = OwnerLocalEntity(input.login, input.avatarUrl)
}
