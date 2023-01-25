package com.mecofarid.trending.ui.trending

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.mecofarid.trending.domain.features.trending.ui.UiState

@BindingAdapter("isLoading")
fun isLoading(view: View, state: UiState) {
    view.isVisible = state == UiState.Loading
}

@BindingAdapter("isNoData")
fun isNoData(view: View, state: UiState) {
    view.isVisible = state == UiState.NoData
}

@BindingAdapter("isSuccess")
fun isSuccess(view: View, state: UiState) {
    view.isVisible = state is UiState.Success
}
