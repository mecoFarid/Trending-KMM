package com.mecofarid.trending.app

import android.app.Application
import com.mecofarid.trending.di.app.AppComponent
import com.mecofarid.trending.di.app.AppModule
import com.mecofarid.trending.features.trending.TrendingComponent
import com.mecofarid.trending.libs.db.sqldelight.DatabaseDriverFactory

class TrendingApplication: Application(), AppComponent {
    private val internalAppComponent by lazy { AppModule(DatabaseDriverFactory(this)) }

    override fun trendingComponent(): TrendingComponent = internalAppComponent.trendingComponent()
}

fun Application.appComponent() = this as AppComponent
