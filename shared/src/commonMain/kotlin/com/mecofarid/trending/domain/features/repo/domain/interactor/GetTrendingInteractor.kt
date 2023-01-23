package com.mecofarid.trending.domain.features.repo.domain.interactor

import com.mecofarid.trending.domain.features.repo.domain.model.Trending

class GetTrendingInteractor(private val trendingRepository: com.mecofarid.trending.domain.common.data.Repository<List<Trending>>) {
    suspend operator fun invoke(query: com.mecofarid.trending.domain.common.data.Query, operation: com.mecofarid.trending.domain.common.data.Operation) = trendingRepository.get(query, operation)
}
