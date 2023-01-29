package com.mecofarid.trending.ui.trending.view

import androidx.databinding.ObservableBoolean
import com.mecofarid.trending.features.trending.domain.model.Trending

data class TrendingView(val trending: Trending) {
    val isExpanded: ObservableBoolean = ObservableBoolean(false)

    fun toggleExpansion(){
        isExpanded.set(!isExpanded.get())
    }
}
