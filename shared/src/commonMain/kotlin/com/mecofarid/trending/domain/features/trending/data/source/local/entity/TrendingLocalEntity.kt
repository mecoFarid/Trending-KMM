package com.mecofarid.trending.domain.features.trending.data.source.local.entity

//@Entity
data class TrendingLocalEntity(
    /*@PrimaryKey(autoGenerate = true)*/ val id: Int = 0,
    val name: String,
    val language: String?,
    val stargazersCount: Long,
    val description: String?,
    /*@Embedded*/ val owner: OwnerLocalEntity
){
    data class OwnerLocalEntity(
        val login: String,
        val avatarUrl: String?,
    )
}
