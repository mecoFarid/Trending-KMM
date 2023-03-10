package com.mecofarid.trending.features.trending.domain.model

data class Trending(
    val trendingId: Long,
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
