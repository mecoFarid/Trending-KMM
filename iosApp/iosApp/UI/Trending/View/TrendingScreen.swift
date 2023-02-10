//
//  TrendingView.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import SwiftUI
import shared

struct TrendingScreen: View {
    
    @EnvironmentObject var viewModel: TrendingViewModel
    var uiState: UiState { viewModel.liveUiState }
    
    var body: some View {
        ZStack(alignment: .bottomTrailing){
            VStack(alignment: .center){
                switch uiState {
                case let success as UiState.Success:
                    TrendingList(trendingList: success.trendingList)
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
        .padding([.leading, .trailing])
    }
}

 struct TrendingList: View {

     let trendingList: [Trending]

     var body: some View {
         ScrollView{
             LazyVStack{
                 ForEach(trendingList) { trending in
                     TrendingItem(trending: trending)
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
                .frame(
                    width: Dimens.gu_4.rawValue,
                    height: Dimens.gu_4.rawValue
                )
                .padding(.all, Dimens.gu.rawValue)
                .background(Color.orange)
                .clipShape(
                    RoundedRectangle(
                        cornerRadius: Dimens.gu_2.rawValue)
                )
                .shadow(
                    radius: Dimens.gu_0_5.rawValue,
                    y: Dimens.gu_0_5.rawValue
                )
        }
    }
}

struct TrendingScreen_Previews: PreviewProvider {
    static let interactor = GetTrendingInteractor(
        trendingRepository: MockRepository(
            result: {
                EitherRight( value: NSArray(array: [
                    TrendingObjectMotherKt.anyTrending(),
                    TrendingObjectMotherKt.anyTrending()
                ]))
            }
        )
    )
    static var previews: some View {
        TrendingScreen().environmentObject(TrendingViewModel(trendingInteractor:interactor))
    }
}

extension Trending: Identifiable{
    public var id: Int {Int(trendingId)}
}
