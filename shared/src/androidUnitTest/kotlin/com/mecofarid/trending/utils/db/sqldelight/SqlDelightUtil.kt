package com.mecofarid.trending.utils.db.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.mecofarid.trending.libs.db.sqldelight.TrendingDatabase

internal actual fun testSqlDelightDriver(): SqlDriver =
    // FixMe: Should use AndroidSqliteDriver but it requires context and context is only available
    //  in androidAndroidTest and test cases on that directory is not working in KMM
    JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        .also { TrendingDatabase.Schema.create(it) }