package com.mecofarid.trending.libs.network.ktor

import com.mecofarid.trending.common.data.NetworkException
import com.mecofarid.trending.common.data.datasource.network.NetworkService
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.github.com"

class NetworkKtorModule: NetworkComponent {
    private val json by lazy {
        Json {
            ignoreUnknownKeys = true
        }
    }
    private val client by lazy {
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(json)
            }
            defaultRequest {
                url(BASE_URL)
            }

            HttpResponseValidator {
                validateResponse {
                    if (it.status.isSuccess().not())
                        throw NetworkException.HttpException(it.status.value)
                }
                handleResponseExceptionWithRequest { cause, request ->
                    if (cause is IOException)
                        throw NetworkException.ConnectionException(cause)
                }
            }
        }
    }

    override fun trendingService(): NetworkService<List<TrendingRemoteEntity>> =
        TrendingService(client)
}