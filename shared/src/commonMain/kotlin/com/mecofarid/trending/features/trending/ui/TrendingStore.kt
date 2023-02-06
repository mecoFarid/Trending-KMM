package com.mecofarid.trending.features.trending.ui

import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.ext.observe
import com.mecofarid.trending.common.ui.store.Store
import com.mecofarid.trending.features.trending.data.query.GetAllTrendingQuery
import com.mecofarid.trending.features.trending.domain.interactor.GetTrendingInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrendingStore(private val trendingInteractor: GetTrendingInteractor): Store() {

    private val internalUiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = internalUiState

    private var state: UiState = UiState.Loading
        set(value) {
            field = value
            internalUiState.tryEmit(value)
        }

    init {
        loadData(Operation.CacheElseSyncMainOperation)
    }

    fun observeUiState(block: (UiState) -> Unit) = uiState.observe(scope, block)

    fun refresh() {
        if (state == UiState.Loading)
            return
        loadData(Operation.SyncMainOperation)
    }

    private fun loadData(operation: Operation) {
        state = UiState.Loading
        scope.launch {
            state = trendingInteractor(GetAllTrendingQuery(), operation)
                .fold(
                    { UiState.NoData },
                    { UiState.Success(it) }
                )
        }
    }
}