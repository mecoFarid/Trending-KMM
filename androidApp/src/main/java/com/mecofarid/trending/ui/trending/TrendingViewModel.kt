package com.mecofarid.trending.ui.trending

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mecofarid.trending.app.appComponent
import com.mecofarid.trending.common.ui.store.StoreViewModel
import com.mecofarid.trending.features.trending.domain.interactor.GetTrendingInteractor
import com.mecofarid.trending.features.trending.ui.TrendingStore

class TrendingViewModel(
    trendingInteractor: GetTrendingInteractor
): StoreViewModel<TrendingStore>, ViewModel() {
    override val store: TrendingStore = TrendingStore(trendingInteractor)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as Application)
                val interactor = app.appComponent().trendingComponent().getTrendingInteractor()
                TrendingViewModel(interactor)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        store.clear()
    }
}