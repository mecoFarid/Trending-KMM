package com.mecofarid.trending.features.trending.data.mapper

import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity.OwnerLocalEntity
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.features.trending.domain.model.Trending.Owner

class TrendingToTrendingLocalEntityMapper(
    private val ownerMapper: Mapper<Owner, OwnerLocalEntity>
): Mapper<Trending, TrendingLocalEntity> {

    override fun map(input: Trending): TrendingLocalEntity =
        with(input) {
            TrendingLocalEntity(
                trendingId,
                name,
                language,
                stargazersCount,
                descriptionText,
                ownerMapper.map(owner)
            )
        }
}

class OwnerToOwnerLocalEntityMapper:
    Mapper<Owner, OwnerLocalEntity> {
    override fun map(input: Owner): OwnerLocalEntity = OwnerLocalEntity(input.login, input.avatarUrl)
}
