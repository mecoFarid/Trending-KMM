package com.mecofarid.trending.mocks.feature.repo

import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.mocks.randomInt
import com.mecofarid.trending.mocks.randomLong
import com.mecofarid.trending.mocks.randomString
import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.features.trending.domain.model.Trending

fun anyTrendingLocalEntity() =
    TrendingLocalEntity(
        randomInt(),
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
        randomInt(),
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
        randomInt(),
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