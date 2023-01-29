package com.mecofarid.trending.features.trending.data.source.remote.service

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Mapper
import com.mecofarid.trending.common.either.Either
import com.mecofarid.trending.common.either.asEither
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingQuery
import com.mecofarid.trending.features.trending.data.source.remote.entity.RepoResponseRemoteEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

private const val QUERY_KEY = "q"

class TrendingService(
    private val client: HttpClient,
    private val exceptionMapper: Mapper<DataException, DataException>
) {

    suspend fun searchRepos(query: GetAllTrendingQuery): Either<DataException, RepoResponseRemoteEntity> =
        executeRequest {
            client.get {
                url("search/repositories")
                parameter(QUERY_KEY, query.query)
            }.body()
        }

    private inline fun <Right> executeRequest(block: () -> Right): Either<DataException, Right> =
        asEither<DataException, Right> {
            block()
        }.mapLeft {
            exceptionMapper.map(it)
        }
}