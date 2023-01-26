package com.mecofarid.trending.common.ui.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mecofarid.trending.features.trending.ui.UiState

@BindingAdapter("enablePullRefresh")
fun enablePullRefresh(view: SwipeRefreshLayout, state: UiState) {
    view.isEnabled = state !is UiState.Loading
    view.isRefreshing = false
}
