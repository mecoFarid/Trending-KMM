package com.mecofarid.trending.features.trending.data.query

import com.mecofarid.trending.common.data.Query

data class GetAllTrendingQuery(val query: String = "language=+sort:stars"): Query
