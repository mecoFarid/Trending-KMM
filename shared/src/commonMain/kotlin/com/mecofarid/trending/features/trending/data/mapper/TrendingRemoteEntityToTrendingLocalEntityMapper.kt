package com.mecofarid.trending.features.trending.data.mapper

import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity.OwnerRemoteEntity
import com.mecofarid.trending.features.trending.domain.model.Trending

class TrendingRemoteEntityToTrendingMapper(
    private val ownerMapper: Mapper<OwnerRemoteEntity, Trending.Owner>
): Mapper<TrendingRemoteEntity, Trending> {

    override fun map(input: TrendingRemoteEntity): Trending =
        with(input){
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

class OwnerRemoteEntityToOwnerMapper:
    Mapper<OwnerRemoteEntity, Trending.Owner> {

    override fun map(input: OwnerRemoteEntity): Trending.Owner =
        Trending.Owner(input.login, input.avatarUrl)
}
