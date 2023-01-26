package com.mecofarid.trending.features.trending.data.source.remote.service

import com.mecofarid.trending.features.trending.data.source.remote.entity.RepoResponseRemoteEntity

interface TrendingService {
//    @GET("search/repositories")
    suspend fun searchRepos(/*@Query("q")*/ query: String): RepoResponseRemoteEntity
}
