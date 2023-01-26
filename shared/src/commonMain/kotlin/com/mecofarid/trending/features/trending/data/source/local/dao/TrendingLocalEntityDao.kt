package com.mecofarid.trending.features.trending.data.source.local.dao

import com.mecofarid.trending.features.trending.data.source.local.entity.TrendingLocalEntity

//@Dao
interface TrendingLocalEntityDao {

//    @Query("SELECT * FROM repolocalentity")
    suspend fun getAllTrendingRepos(): List<TrendingLocalEntity>

//    @Transaction
    suspend fun deleteAllTrendingReposAndInsert(repos: List<TrendingLocalEntity>) {
        deleteAll()
        insertAll(repos)
    }

//    @Query("DELETE FROM repolocalentity")
    suspend fun deleteAll()

//    @Insert
    suspend fun insertAll(users: List<TrendingLocalEntity>)
}
