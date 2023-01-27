package com.mecofarid.trending.libs.db.sqldelight

import app.cash.sqldelight.db.SqlDriver

internal const val DB_NAME = "trending.db"

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): TrendingDatabase {
    return TrendingDatabase(driverFactory.createDriver())
}