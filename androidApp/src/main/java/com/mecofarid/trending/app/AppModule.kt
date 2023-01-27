package com.mecofarid.trending.app

import android.app.Application
import com.mecofarid.trending.di.AppComponent
import com.mecofarid.trending.features.trending.RepoComponent

class AppModule(application: Application): AppComponent {

//    private val dbComponent by lazy {
//        Room.databaseBuilder(
//            application,
//            DbRoomModule::class.java,
//            DB_NAME
//        ).build()
//    }
//    private val networkComponent by lazy {
//        val json = Json{
//            ignoreUnknownKeys = true
//        }
//        NetworkRetrofitModule(KotlinxJsonConverter(json))
//    }
//
//    private val module by lazy {
//        RepoModule(dbComponent, networkComponent)
//    }
    override fun repoComponent(): RepoComponent = TODO() //module
}
