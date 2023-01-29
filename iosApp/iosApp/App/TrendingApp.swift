//
//  TrendingApp.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import SwiftUI
import shared

 private var appComponent: AppComponent = { AppModule(databaseDriverFactory: DatabaseDriverFactory()) }()

@main
struct TrendingApp: App {

    var body: some Scene {
        WindowGroup{
            TrendingScreen().environmentObject(TrendingViewModel(trendingInteractor: appComponent.trendingComponent().getTrendingInteractor()))
        }
    }
}
