package com.mecofarid.trending.features.trending.data.mapper

import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity.OwnerLocalEntity
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.features.trending.domain.model.Trending.Owner

class RepoLocalEntityToRepoMapper(
    private val ownerMapper: Mapper<OwnerLocalEntity, Owner>
): Mapper<TrendingLocalEntity, Trending> {

    override fun map(input: TrendingLocalEntity): Trending =
        with(input) {
            Trending(
                id,
                name,
                language,
                stargazersCount,
                description,
                ownerMapper.map(owner)
            )
        }
}

class OwnerLocalEntityToOwnerMapper:
    Mapper<OwnerLocalEntity, Owner> {

    override fun map(input: OwnerLocalEntity): Owner = Owner(input.login, input.avatarUrl)
}
