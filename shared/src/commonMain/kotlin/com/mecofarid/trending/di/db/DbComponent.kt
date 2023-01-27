package com.mecofarid.trending.di.db

import com.mecofarid.trending.features.trending.data.source.local.dao.TrendingLocalEntityDao

interface DbComponent{
    fun trendingLocalEntityDao(): TrendingLocalEntityDao
}
