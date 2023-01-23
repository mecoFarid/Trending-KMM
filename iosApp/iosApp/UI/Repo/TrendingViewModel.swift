//
//  TrendingViewModel.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import Foundation
import shared

@MainActor
class TrendingViewModel: CommonTrendingViewModel, ObservableObject {

    @Published private(set) var liveUiState: State!

    override init(repoInteractor: GetTrendingInteractor) {
        super.init(repoInteractor: repoInteractor)
        observeUiState{ state in
            self.liveUiState = state
        }
    }

    deinit {
        clear()
    }
}
