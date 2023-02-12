package com.mecofarid.trending.features.trending.data.source.local.dao

import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity
import com.mecofarid.trending.libs.db.sqldelight.TrendingDatabase

class TrendingLocalEntityDao(private val trendingDatabase: TrendingDatabase) {

    private val trendingEntityQueries = trendingDatabase.trendingEntityQueries
    private val ownerEntityQueries = trendingDatabase.ownerEntityQueries

    fun getAllTrending(): List<TrendingLocalEntity> =
        trendingEntityQueries.selectAll { id, name, language, stargazersCount, description, _, login, avatarUrl ->
            TrendingLocalEntity(
                id, name, language, stargazersCount, description,
                TrendingLocalEntity.OwnerLocalEntity(login!!, avatarUrl)
            )
        }.executeAsList()

    fun deleteAllTrendingAndInsert(trendingList: List<TrendingLocalEntity>) {
        deleteAll()
        insertAll(trendingList)
    }

    private fun deleteAll() {
        trendingEntityQueries.deleteAll()
        ownerEntityQueries.deleteAll()
    }

    private fun insertAll(entityList: List<TrendingLocalEntity>) {
        trendingDatabase.transaction {
            entityList.forEach {
                it.apply {
                    insertTrending()
                    owner.insertOwner(id)
                }
            }
        }
    }

    private fun TrendingLocalEntity.insertTrending() {
        trendingEntityQueries.insert(id, name, language, stargazersCount, description)
    }

    private fun TrendingLocalEntity.OwnerLocalEntity.insertOwner(trendingId: Long) {
        ownerEntityQueries.insert(trendingId, login, avatarUrl)
    }
}
