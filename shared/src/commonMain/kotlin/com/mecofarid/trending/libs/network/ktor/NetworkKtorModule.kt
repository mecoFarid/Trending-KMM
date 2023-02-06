package com.mecofarid.trending.libs.network.ktor

import com.mecofarid.trending.common.data.NetworkException
import com.mecofarid.trending.common.data.datasource.network.NetworkService
import com.mecofarid.trending.di.network.NetworkComponent
import com.mecofarid.trending.features.trending.data.source.remote.entity.TrendingRemoteEntity
import com.mecofarid.trending.features.trending.data.source.remote.service.TrendingService
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.errors.*
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
                handleResponseExceptionWithRequest { cause, _ ->
                    throw if (cause is IOException)
                         NetworkException.ConnectionException(cause)
                    else if (cause is ResponseException)
                        throw NetworkException.HttpException(cause.response.status.value)
                    else throw cause
                }
            }
        }
    }

    override fun trendingService(): NetworkService<List<TrendingRemoteEntity>> =
        TrendingService(client)
}