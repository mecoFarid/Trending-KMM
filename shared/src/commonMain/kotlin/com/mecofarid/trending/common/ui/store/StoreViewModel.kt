package com.mecofarid.trending.common.ui.store

interface StoreViewModel<S: Store>{
    val store: S

    fun clear(){
        store.clear()
    }
}