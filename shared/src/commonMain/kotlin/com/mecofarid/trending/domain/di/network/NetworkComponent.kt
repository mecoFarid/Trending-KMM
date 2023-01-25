package com.mecofarid.trending.domain.di.network

import com.mecofarid.trending.domain.features.trending.data.source.remote.service.TrendingService

interface NetworkComponent {
    fun repoService(): TrendingService
}
