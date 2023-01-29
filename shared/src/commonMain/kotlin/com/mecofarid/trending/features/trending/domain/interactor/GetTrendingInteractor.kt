package com.mecofarid.trending.features.trending.domain.interactor

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.data.Query
import com.mecofarid.trending.common.data.repository.Repository
import com.mecofarid.trending.features.trending.domain.model.Trending

class GetTrendingInteractor(private val trendingRepository: Repository<List<Trending>, DataException>) {
    suspend operator fun invoke(query: Query, operation: Operation) = trendingRepository.get(query, operation)
}
