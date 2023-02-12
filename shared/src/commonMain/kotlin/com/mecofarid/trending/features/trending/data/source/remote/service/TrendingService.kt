package com.mecofarid.trending.features.trending.data.source.remote.service

import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.datasource.network.NetworkService
import com.mecofarid.trending.common.data.validateQuery
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingQuery
import com.mecofarid.trending.features.trending.data.query.TrendingQuery
import com.mecofarid.trending.features.trending.data.source.remote.entity.RepoResponseRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.request.HttpRequestBuilder


private const val QUERY_KEY = "q"
private const val SEARCH_URL = "search/repositories"

class TrendingService(private val client: HttpClient): NetworkService<List<TrendingRemoteEntity>> {

    override suspend fun get(query: Query): List<TrendingRemoteEntity> =
        client.get {
            applyQuery(query)
        }.body<RepoResponseRemoteEntity>().items

    override suspend fun put(
        query: Query,
        data: List<TrendingRemoteEntity>
    ): List<TrendingRemoteEntity> =
        throw UnsupportedOperationException("Put is not supported")

    private fun HttpRequestBuilder.applyQuery(query: Query){
        query.validateQuery<TrendingQuery>()
        when (query) {
            is GetAllTrendingQuery -> {
                url(SEARCH_URL)
                parameter(QUERY_KEY, query.query)
            }
        }
    }
}
