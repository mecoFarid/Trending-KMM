package com.mecofarid.trending.features.trending.ui

import com.mecofarid.trending.features.trending.domain.model.Trending

sealed class UiState {
    object Loading : UiState()
    object NoData : UiState()
    data class Success(val trendingList: List<Trending>) : UiState()
}