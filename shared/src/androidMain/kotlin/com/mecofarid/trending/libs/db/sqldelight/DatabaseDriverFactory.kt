package com.mecofarid.trending.libs.db.sqldelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(dbName: String): SqlDriver =
        AndroidSqliteDriver(TrendingDatabase.Schema, context, dbName)
}
