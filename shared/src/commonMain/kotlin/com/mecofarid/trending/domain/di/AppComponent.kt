package com.mecofarid.trending.domain.di

import com.mecofarid.trending.domain.features.trending.RepoComponent

interface AppComponent{
    fun repoComponent(): RepoComponent
}
