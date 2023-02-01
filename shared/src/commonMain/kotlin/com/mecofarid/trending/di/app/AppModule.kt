package com.mecofarid.trending.di.app

import com.mecofarid.trending.features.trending.TrendingComponent
import com.mecofarid.trending.features.trending.TrendingModule
import com.mecofarid.trending.libs.db.sqldelight.DatabaseDriverFactory
import com.mecofarid.trending.libs.db.sqldelight.DbSqldelightModule
import com.mecofarid.trending.libs.network.ktor.NetworkKtorModule

class AppModule(databaseDriverFactory: DatabaseDriverFactory): AppComponent {

    private val module by lazy {
        TrendingModule(DbSqldelightModule(databaseDriverFactory), NetworkKtorModule())
    }
    override fun trendingComponent(): TrendingComponent = module
}