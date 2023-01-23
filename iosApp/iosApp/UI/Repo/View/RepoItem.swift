//
//  RepoItem.swift
//  Trending
//
//  Created by Farid Mammadov on 07.01.23.
//
//
// import SwiftUI
// import shared
//
// struct RepoItem: View {
//
//     let trending: Trending
//     @State private var isExpanded: Bool = false
//
//     var body: some View {
//         VStack(alignment: .leading){
//             HStack{
//                 Avatar(url: trending.owner.avatarUrl)
//
//                 VStack(alignment: .leading){
//                     Text(trending.owner.login)
//                         .font(.subheadline)
//                         .lineLimit(1)
//
//                     Text(trending.name)
//                         .font(.caption)
//                         .lineLimit(1)
//                 }
//                 Spacer()
//             }
//
//             if isExpanded {
//                 DetailsView(trending: trending)
//             }
//
//         }
//         .contentShape(Rectangle())
//         .onTapGesture {
//             withAnimation{
//                 isExpanded.toggle()
//             }
//         }
//     }
// }
//
// struct RepoItem_Previews: PreviewProvider {
//     static var previews: some View {
//         RepoItem(trending: anyTrending())
//     }
// }
