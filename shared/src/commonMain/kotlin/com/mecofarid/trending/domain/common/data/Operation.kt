package com.mecofarid.trending.domain.common.data

sealed class Operation{

    // Sync cache data source with main datasource
    object SyncMainOperation: com.mecofarid.trending.domain.common.data.Operation()

    // Get data from cache, if it fails sync cache datasource with main datasource
    object CacheElseSyncMainOperation: com.mecofarid.trending.domain.common.data.Operation()
}
