package com.mecofarid.trending.di.network

import com.mecofarid.trending.common.data.datasource.network.NetworkService
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity

interface NetworkComponent {
    fun trendingService(): NetworkService<List<TrendingRemoteEntity>>
}
