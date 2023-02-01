package com.mecofarid.trending.libs.db.sqldelight

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(dbName: String): SqlDriver
}