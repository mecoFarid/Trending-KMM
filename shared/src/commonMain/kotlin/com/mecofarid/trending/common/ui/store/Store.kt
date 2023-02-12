package com.mecofarid.trending.common.ui.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class Store {
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun clear() {
        scope.cancel()
    }
}
