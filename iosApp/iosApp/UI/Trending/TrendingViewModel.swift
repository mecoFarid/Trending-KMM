//
//  TrendingViewModel.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import Foundation
import shared

@MainActor
class TrendingViewModel: StoreViewModel, ObservableObject{
    nonisolated var store: Store { TrendingStore(repoInteractor: repoInteractor) }
    lazy private(set) var trendingStore = { self.store as! TrendingStore }()
    
    
    @Published private(set) var liveUiState: UiState!
    private let repoInteractor: GetTrendingInteractor

    init(repoInteractor: GetTrendingInteractor) {
        self.repoInteractor = repoInteractor
        trendingStore.observeUiState{ state in
            self.liveUiState = state
        }
    }
    
    deinit {
        clear()
    }
    
    nonisolated func clear() {
        store.clear()
    }
}
