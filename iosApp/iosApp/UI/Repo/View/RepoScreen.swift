//
//  RepoView.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//
//
//  import SwiftUI
//
// struct RepoScreen: View {
//
//     @EnvironmentObject var viewModel: TrendingViewModel
//     var uiState: UiState { viewModel.uiState }
//
//     var body: some View {
//         ZStack(alignment: .bottomTrailing){
//             VStack(alignment: .center){
//                 switch uiState {
//                 case .success(let data):
//                     RepoList(trendingList: data)
//                 case .loading:
//                     LottieView(name: LottieAnimation.loading.rawValue)
//                         .frame(width: Dimens.gu_30.rawValue, height: Dimens.gu_30.rawValue)
//                 case .error:
//                     LottieView(name: LottieAnimation.noData.rawValue)
//                         .frame(width: Dimens.gu_30.rawValue, height: Dimens.gu_30.rawValue)
//                 }
//             }
//             .frame(maxWidth: .infinity, maxHeight: .infinity)
//
//             if case .loading = uiState {} else {
//                 RefreshButton(viewModel: viewModel)
//             }
//         }
//         .padding()
//         .onAppear{
//             viewModel.loadData()
//         }
//         .onDisappear{
//             viewModel.cancel()
//         }
//     }
// }
//
// struct RepoList: View {
//
//     let trendingList: [Trending]
//
//     var body: some View {
//         ScrollView{
//             LazyVStack{
//                 ForEach(trendingList) { trending in
//                     RepoItem(trending: trending)
//                     Divider()
//                 }
//             }
//             .padding([.bottom], Dimens.gu_6.rawValue)
//         }
//     }
// }
//
// struct RefreshButton: View{
//
//     let viewModel: TrendingViewModel
//
//     var body: some View{
//         Button{
//             viewModel.refreshData()
//         } label: {
//             Image(systemName: "goforward")
//                 .foregroundColor(Color.white)
//                 .frame(width: Dimens.gu_6.rawValue)
//                 .padding(.all, Dimens.gu.rawValue)
//                 .background(Color.orange)
//                 .clipShape(Circle())
//         }
//     }
// }
//
// struct RepoScreen_Previews: PreviewProvider {
//     static let interactor = GetTrendingInteractor(
//         MockTrendingRepository{
//             //            sleep(1_000)
//             //            return Result.failure(DataException())
//
//             return Result.success([anyTrending()])
//         }
//     )
//     static var previews: some View {
//         RepoScreen().environmentObject(TrendingViewModel(interactor))
//     }
// }
