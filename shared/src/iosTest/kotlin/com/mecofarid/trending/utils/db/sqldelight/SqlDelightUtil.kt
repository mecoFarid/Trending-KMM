package com.mecofarid.trending.utils.db.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import com.mecofarid.trending.libs.db.sqldelight.TrendingDatabase

actual fun testSqlDelightDriver(): SqlDriver = NativeSqliteDriver(
        DatabaseConfiguration(
            name = null,
            version = 1,
            create = { connection ->
                wrapConnection(connection) { TrendingDatabase.Schema.create(it) }
            },
            inMemory = true
        ),
        1
    )
