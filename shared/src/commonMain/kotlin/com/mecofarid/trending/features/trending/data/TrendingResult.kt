package com.mecofarid.trending.features.trending.data

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.either.Either

typealias TrendingResult<T> = Either<DataException, List<T>>