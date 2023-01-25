package com.mecofarid.trending.common.data

sealed class Operation{

    // Sync cache data source with main datasource
    object SyncMainOperation: Operation()

    // Get data from cache, if it fails sync cache datasource with main datasource
    object CacheElseSyncMainOperation: Operation()
}
