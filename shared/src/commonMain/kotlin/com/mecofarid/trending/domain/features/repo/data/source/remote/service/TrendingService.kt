package com.mecofarid.trending.domain.features.repo.data.source.remote.service

import com.mecofarid.trending.domain.features.repo.data.source.remote.entity.RepoResponseRemoteEntity

interface TrendingService {
//    @GET("search/repositories")
    suspend fun searchRepos(/*@Query("q")*/ query: String): RepoResponseRemoteEntity
}
