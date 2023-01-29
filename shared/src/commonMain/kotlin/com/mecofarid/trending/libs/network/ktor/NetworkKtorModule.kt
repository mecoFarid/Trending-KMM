package com.mecofarid.trending.libs.network.ktor

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService
import io.ktor.client.HttpClient

class NetworkKtorModule(
    private val client: HttpClient,
    private val exceptionMapper: Mapper<DataException, DataException>
): NetworkComponent {
    override fun trendingService(): TrendingService = TrendingService(client, exceptionMapper)
}