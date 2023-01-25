//
//  RepoView.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import SwiftUI
import shared

struct RepoScreen: View {
    
    @EnvironmentObject var viewModel: TrendingViewModel
    var uiState: UiState { viewModel.liveUiState }
    
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            VStack(alignment: .center){
                switch uiState {
                case let success as UiState.Success:
                    RepoList(trendingList: success.trendingList)
                case _ as UiState.Loading:
                    LottieView(name: LottieAnimation.loading.rawValue)
                        .frame(width: Dimens.gu_30.rawValue, height: Dimens.gu_30.rawValue)
                case _ as UiState.NoData:
                    LottieView(name: LottieAnimation.noData.rawValue)
                        .frame(width: Dimens.gu_30.rawValue, height: Dimens.gu_30.rawValue)
                default:
                    fatalError("Invalid UiState: \(uiState)")
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
            if case UiState.Loading() = uiState {} else {
                RefreshButton(viewModel: viewModel)
            }
        }
        .padding()
    }
}

 struct RepoList: View {

     let trendingList: [Trending]

     var body: some View {
         ScrollView{
             LazyVStack{
                 ForEach(trendingList) { trending in
                     RepoItem(trending: trending)
                     Divider()
                 }
             }
             .padding([.bottom], Dimens.gu_6.rawValue)
         }
     }
 }

struct RefreshButton: View{
    
    let viewModel: TrendingViewModel
    
    var body: some View{
        Button{
            viewModel.trendingStore.refresh()
        } label: {
            Image(systemName: "goforward")
                .foregroundColor(Color.white)
                .frame(width: Dimens.gu_6.rawValue)
                .padding(.all, Dimens.gu.rawValue)
                .background(Color.orange)
                .clipShape(Circle())
        }
    }
}

struct RepoScreen_Previews: PreviewProvider {
    static let interactor = GetTrendingInteractor(
        trendingRepository: MockRepository(
            result: {
                NSArray(array: [
                    TrendingObjectMotherKt.anyTrending(),
                    TrendingObjectMotherKt.anyTrending()
                ])
            }
        )
    )
    static var previews: some View {
        RepoScreen().environmentObject(TrendingViewModel(repoInteractor: interactor))
    }
}

extension Trending: Identifiable{
    public var id: Int {Int(trendingId)}
}
