package com.mecofarid.trending.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mecofarid.trending.common.ui.store.StoreViewModel
import com.mecofarid.trending.features.trending.domain.interactor.GetTrendingInteractor
import com.mecofarid.trending.features.trending.ui.TrendingStore

class TrendingViewModel(
    repoInteractor: GetTrendingInteractor
): StoreViewModel<TrendingStore>, ViewModel() {
    override val store: TrendingStore = TrendingStore(repoInteractor)
    val uiState = store.uiState.asLiveData()

    companion object {
        fun factory(repoInteractor: GetTrendingInteractor) = viewModelFactory {
            initializer {
                TrendingViewModel(repoInteractor)
            }
        }
    }
}