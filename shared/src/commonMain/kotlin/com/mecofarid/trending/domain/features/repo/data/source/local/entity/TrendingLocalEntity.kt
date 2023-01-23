package com.mecofarid.trending.domain.features.repo.data.source.local.entity

//@Entity
data class TrendingLocalEntity(
    val name: String,
    val language: String?,
    val stargazersCount: Long,
    val description: String?,
    /*@Embedded*/ val owner: OwnerLocalEntity,
    /*@PrimaryKey(autoGenerate = true)*/ val id: Int = 0
){
    data class OwnerLocalEntity(
        val login: String,
        val avatarUrl: String?,
    )
}
