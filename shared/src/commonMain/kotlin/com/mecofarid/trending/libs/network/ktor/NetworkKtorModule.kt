package com.mecofarid.trending.libs.network.ktor

import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService
import io.ktor.client.HttpClient

class NetworkKtorModule(private val client: HttpClient): NetworkComponent {
    override fun trendingService(): TrendingService = TrendingService(client)
}