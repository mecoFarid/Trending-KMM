package com.mecofarid.trending.domain.di.db

import com.mecofarid.trending.domain.features.repo.data.source.local.dao.TrendingLocalEntityDao

interface DbComponent{
    fun repoLocalEntityDao(): TrendingLocalEntityDao
}
