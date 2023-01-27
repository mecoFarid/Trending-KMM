package com.mecofarid.trending.features.trending.data.source.local.dao

import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import features.trending.TrendingEntityQueries

class TrendingLocalEntityDao(private val trendingEntityQueries: TrendingEntityQueries) {

    fun getAllTrending(): List<TrendingLocalEntity> =
        trendingEntityQueries.selectAll { id, name, language, stargazersCount, description, login, avatarUrl ->
            TrendingLocalEntity(
                id, name, language, stargazersCount, description,
                TrendingLocalEntity.OwnerLocalEntity(login, avatarUrl)
            )
        }.executeAsList()

    fun deleteAllTrendingAndInsert(trendingList: List<TrendingLocalEntity>) {
        deleteAll()
        insertAll(trendingList)
    }

    private fun deleteAll() = trendingEntityQueries.deleteAll()

    private fun insertAll(users: List<TrendingLocalEntity>) {
        trendingEntityQueries.transaction {
            users.forEach {
                it.apply {
                    trendingEntityQueries.insert(id, name, language, stargazersCount, description)
                }
            }
        }
    }
}
