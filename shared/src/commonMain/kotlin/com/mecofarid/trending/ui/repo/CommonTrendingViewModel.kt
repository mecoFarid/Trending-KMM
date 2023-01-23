package com.mecofarid.trending.ui.repo

import com.mecofarid.trending.domain.common.data.DataException
import com.mecofarid.trending.domain.common.data.Operation
import com.mecofarid.trending.domain.common.ext.observe
import com.mecofarid.trending.domain.features.repo.data.query.GetAllTrendingReposQuery
import com.mecofarid.trending.domain.features.repo.domain.interactor.GetTrendingInteractor
import com.mecofarid.trending.domain.features.repo.domain.model.Trending
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class CommonTrendingViewModel(private val repoInteractor: GetTrendingInteractor){

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val internalUiState = MutableStateFlow<State>(State.Loading)
    protected val uiState: Flow<State> = internalUiState

    private var state: State = State.Loading
        set(value) {
            field = value
            internalUiState.tryEmit(value)
        }

    init {
        loadData(Operation.CacheElseSyncMainOperation)
    }

    protected fun observeUiState(block:(State) -> Unit) = uiState.observe(scope, block)

    fun refresh() {
        if (state == State.Loading)
            return
        loadData(Operation.SyncMainOperation)
    }

    fun clear() {
        scope.cancel()
    }

    private fun loadData(operation: Operation) {
        state = State.Loading
        scope.launch {
            state = try {
                val data = repoInteractor(GetAllTrendingReposQuery, operation)
                State.Success(data)
            } catch (ignore: DataException.DataNotFoundException) {
                State.NoData
            }
        }
    }

    sealed class State {
        object Loading : State()
        object NoData : State()
        data class Success(val trendingList: List<Trending>) : State()
    }
}