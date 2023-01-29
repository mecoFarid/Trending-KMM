package com.mecofarid.trending.libs.db.sqldelight

import com.mecofarid.trending.di.db.DbComponent
import com.mecofarid.trending.features.trending.data.source.local.dao.TrendingLocalEntityDao

class DbSqldelightModule(private val databaseDriverFactory: DatabaseDriverFactory): DbComponent {
    private val database by lazy {
        createDatabase(databaseDriverFactory)
    }

    override fun trendingLocalEntityDao(): TrendingLocalEntityDao =
        TrendingLocalEntityDao(database)
}