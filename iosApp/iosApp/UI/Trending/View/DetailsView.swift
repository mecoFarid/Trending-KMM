//
//  DetailsView.swift
//  Trending
//
//  Created by Farid Mammadov on 06.01.23.
//

import SwiftUI
import shared

struct DetailsView: View{
    
    let trending: Trending
    
    var body: some View{
        VStack(alignment: .leading, spacing: Dimens.gu.rawValue){
            Text(trending.descriptionText ?? "")
                .font(.caption2)
            
            HStack{
                ImageText(image: "star.fill", imageColor: .orange, text: String(trending.stargazersCount))
                Spacer()
                ImageText(image: "circle.fill", imageColor: .blue, text: trending.language ?? "N/A")
                Spacer()
            }
        }
    }
}

struct DetailsView_Previews: PreviewProvider {
    static var previews: some View {
        DetailsView(trending: TrendingObjectMotherKt.anyTrending())
    }
}
