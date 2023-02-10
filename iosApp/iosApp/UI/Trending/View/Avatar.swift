//
//  Avatar.swift
//  Trending
//
//  Created by Farid Mammadov on 05.01.23.
//

import SwiftUI
import Kingfisher

struct Avatar: View{
    
    let url: String?
    
    var body: some View{
        KFImage(URL(string: url ?? ""))
            .placeholder({Color.gray})
            .resizable()
            .scaledToFit()
            .frame(width: Dimens.gu_4.rawValue, height: Dimens.gu_4.rawValue)
            .clipShape(Circle())
    }
}

struct Avatar_Previews: PreviewProvider {
    static var previews: some View {
        Avatar(url: "")
    }
}
