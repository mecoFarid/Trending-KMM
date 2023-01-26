package com.mecofarid.trending.di.network

import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService

interface NetworkComponent {
    fun repoService(): TrendingService
}
