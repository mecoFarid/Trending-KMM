package com.mecofarid.trending.mocks.feature.trending

import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.mocks.randomLong
import com.mecofarid.trending.mocks.randomString

fun anyTrendingLocalEntity() =
    TrendingLocalEntity(
        randomLong(),
        randomString(),
        randomString(),
        randomLong(),
        randomString(),
        anyOwnerLocalEntity()
    )

fun anyOwnerLocalEntity() =
    TrendingLocalEntity.OwnerLocalEntity(
        randomString(),
        randomString()
    )

fun anyTrendingRemoteEntity() =
    TrendingRemoteEntity(
        randomLong(),
        randomString(),
        randomString(),
        randomLong(),
        randomString(),
        anyOwnerRemoteEntity()
    )

fun anyOwnerRemoteEntity() =
    TrendingRemoteEntity.OwnerRemoteEntity(
        randomString(),
        randomString()
    )

fun anyTrending(): Trending =
    Trending(
        randomLong(),
        randomString(),
        randomString(),
        randomLong(),
        randomString(),
        anyOwner()
    )

fun anyOwner() =
    Trending.Owner(
        randomString(),
        randomString()
    )