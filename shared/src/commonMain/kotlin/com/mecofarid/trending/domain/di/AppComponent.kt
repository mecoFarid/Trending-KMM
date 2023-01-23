package com.mecofarid.trending.domain.di

import com.mecofarid.trending.domain.features.repo.RepoComponent

interface AppComponent{
    fun repoComponent(): RepoComponent
}
