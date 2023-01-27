package com.mecofarid.trending.features.trending.data.source.local.entity

data class TrendingLocalEntity(
    val id: Long = 0,
    val name: String,
    val language: String?,
    val stargazersCount: Long,
    val description: String?,
    val owner: OwnerLocalEntity
){
    data class OwnerLocalEntity(
        val login: String,
        val avatarUrl: String?,
    )
}
