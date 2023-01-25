package com.mecofarid.trending.domain.features.trending.domain.interactor

import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.Repository
import com.mecofarid.trending.domain.features.trending.domain.model.Trending

class GetTrendingInteractor(private val trendingRepository: Repository<List<Trending>>) {
    suspend operator fun invoke(query: Query, operation: Operation) = trendingRepository.get(query, operation)
}
