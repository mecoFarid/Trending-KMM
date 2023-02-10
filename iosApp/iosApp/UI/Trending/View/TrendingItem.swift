//
//  TrendingItem.swift
//  Trending
//
//  Created by Farid Mammadov on 07.01.23.
//

import SwiftUI
import shared

struct TrendingItem: View {
    
    let trending: Trending
    @State private var isExpanded: Bool = false
    
    var body: some View {
        
        VStack(alignment: .leading){
            HStack{
                Avatar(url: trending.owner.avatarUrl)
                
                VStack(alignment: .leading){
                    Text(trending.owner.login)
                        .font(.caption)
                        .lineLimit(1)
                    
                    Text(trending.name)
                        .font(.caption2)
                        .lineLimit(1)
                }
                Spacer()
            }
            
            if isExpanded {
                DetailsView(trending: trending)
                // This is an ugly workaround to show DetailsView's start aligned to end of Avatar
                    .padding(.leading, Dimens.gu_5.rawValue)
            }
            
        }
        .contentShape(Rectangle())
        .onTapGesture {
            withAnimation{
                isExpanded.toggle()
            }
        }
    }
}

struct TrendingItem_Previews: PreviewProvider {
    static var previews: some View {
        TrendingItem(trending: TrendingObjectMotherKt.anyTrending())
    }
}
