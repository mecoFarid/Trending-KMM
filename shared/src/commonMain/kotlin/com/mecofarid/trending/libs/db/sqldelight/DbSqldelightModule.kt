package com.mecofarid.trending.libs.db.sqldelight

import com.mecofarid.trending.di.db.DbComponent
import com.mecofarid.trending.features.trending.data.source.local.dao.TrendingLocalEntityDao

private const val DB_NAME = "trending.db"

class DbSqldelightModule(private val databaseDriverFactory: DatabaseDriverFactory): DbComponent {
    private val database by lazy {
        TrendingDatabase(databaseDriverFactory.createDriver(DB_NAME))
    }

    override fun trendingLocalEntityDao(): TrendingLocalEntityDao =
        TrendingLocalEntityDao(database)
}
