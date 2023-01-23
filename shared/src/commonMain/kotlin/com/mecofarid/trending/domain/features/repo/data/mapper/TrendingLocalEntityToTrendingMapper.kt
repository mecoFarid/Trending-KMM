package com.mecofarid.trending.domain.features.repo.data.mapper

import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.domain.features.repo.data.source.local.entity.TrendingLocalEntity.OwnerLocalEntity
import com.mecofarid.trending.domain.features.repo.domain.model.Trending
import com.mecofarid.trending.domain.features.repo.domain.model.Trending.Owner

class RepoLocalEntityToRepoMapper(
    private val ownerMapper: com.mecofarid.trending.domain.common.data.Mapper<OwnerLocalEntity, Owner>
): com.mecofarid.trending.domain.common.data.Mapper<TrendingLocalEntity, Trending> {

    override fun map(input: TrendingLocalEntity): Trending =
        with(input) {
            Trending(
                name,
                language,
                stargazersCount,
                description,
                ownerMapper.map(owner)
            )
        }
}

class OwnerLocalEntityToOwnerMapper:
    com.mecofarid.trending.domain.common.data.Mapper<OwnerLocalEntity, Owner> {

    override fun map(input: OwnerLocalEntity): Owner = Owner(input.login, input.avatarUrl)
}
