package com.mecofarid.trending.common.data

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface Query

@OptIn(ExperimentalContracts::class)
inline fun <reified T: Query> Query.validateQuery() {
    contract {
        returns() implies (this@validateQuery is T)
    }

    check(this is T) {"$this must be of type ${T::class}"}
}
