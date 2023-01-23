package com.mecofarid.trending.domain.features.repo.data.source.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoResponseRemoteEntity(val items: List<RepoRemoteEntity>)

@Serializable
data class RepoRemoteEntity(
    val name: String,
    val language: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Long,
    val description: String?,
    val owner: OwnerRemoteEntity
){
    @Serializable
    data class OwnerRemoteEntity(
        val login: String,
        @SerialName("avatar_url")
        val avatarUrl: String?
    )
}
