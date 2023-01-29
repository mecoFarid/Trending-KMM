package com.mecofarid.trending.di.app

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.common.data.NetworkException
import com.mecofarid.trending.features.trending.TrendingComponent
import com.mecofarid.trending.features.trending.TrendingModule
import com.mecofarid.trending.libs.db.sqldelight.DatabaseDriverFactory
import com.mecofarid.trending.libs.db.sqldelight.DbSqldelightModule
import com.mecofarid.trending.libs.network.ktor.NetworkKtorModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.github.com"

class AppModule(databaseDriverFactory: DatabaseDriverFactory): AppComponent {
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

    private val networkComponent by lazy {
        val exceptionMapper = object : Mapper<DataException, DataException>{
            override fun map(input: DataException) = DataException.DataNotFoundException(input)
        }
        NetworkKtorModule(client, exceptionMapper)
    }

    private val module by lazy {
        TrendingModule(DbSqldelightModule(databaseDriverFactory), networkComponent)
    }
    override fun trendingComponent(): TrendingComponent = module
}
