package com.mecofarid.trending.common.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(scope: CoroutineScope, block: (T) -> Unit) {
    onEach {
        block(it)
    }.launchIn(scope)
}
