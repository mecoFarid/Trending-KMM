package com.mecofarid.trending.domain.features.trending.ui

import com.mecofarid.trending.common.data.DataException
import com.mecofarid.trending.common.data.Operation
import com.mecofarid.trending.common.ext.observe
import com.mecofarid.trending.common.ui.store.Store
import com.mecofarid.trending.domain.features.trending.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.domain.features.trending.domain.interactor.GetTrendingInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrendingStore(private val repoInteractor: GetTrendingInteractor): Store() {

    private val internalUiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: Flow<UiState> = internalUiState

    private var state: UiState = UiState.Loading
        set(value) {
            field = value
            internalUiState.tryEmit(value)
        }

    init {
        loadData(Operation.CacheElseSyncMainOperation)
    }

    fun observeUiState(block:(UiState) -> Unit) = uiState.observe(scope, block)

    fun refresh() {
        if (state == UiState.Loading)
            return
        loadData(Operation.SyncMainOperation)
    }

    private fun loadData(operation: Operation) {
        state = UiState.Loading
        scope.launch {
            state = try {
                val data = repoInteractor(GetAllTrendingReposQuery, operation)
                UiState.Success(data)
            } catch (ignore: DataException.DataNotFoundException) {
                UiState.NoData
            }
        }
    }
}