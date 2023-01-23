package com.mecofarid.trending.ui.repo

import androidx.lifecycle.asLiveData
import com.mecofarid.trending.domain.features.repo.domain.interactor.GetTrendingInteractor

class TrendingViewModel(
    repoInteractor: GetTrendingInteractor
): CommonTrendingViewModel(repoInteractor){
    val liveUiState = uiState.asLiveData()
}