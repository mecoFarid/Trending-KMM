package com.mecofarid.trending.domain.features.trending.domain.model

data class Trending(
    val trendingId: Int,
    val name: String,
    val language: String?,
    val stargazersCount: Long,
    val descriptionText: String?,
    val owner: Owner
){
    data class Owner(
        val login: String,
        val avatarUrl: String?
    )
}
