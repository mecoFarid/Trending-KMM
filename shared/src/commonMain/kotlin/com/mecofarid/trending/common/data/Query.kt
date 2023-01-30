package com.mecofarid.trending.common.data

import app.cash.sqldelight.db.QueryResult.Unit.value
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface Query

@OptIn(ExperimentalContracts::class)
inline fun <reified T: Query> Query.validateQuery() {
    contract {
        returns() implies (this@validateQuery is T)
    }
    if (!(this is T)) {
        throw IllegalStateException("$this must be of type ${T::class}")
    }
}
