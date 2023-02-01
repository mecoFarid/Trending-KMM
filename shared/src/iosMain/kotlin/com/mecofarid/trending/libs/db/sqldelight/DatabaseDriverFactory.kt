package com.mecofarid.trending.libs.db.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(dbName: String): SqlDriver =
        NativeSqliteDriver(TrendingDatabase.Schema, dbName)
}