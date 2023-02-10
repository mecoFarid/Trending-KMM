//
//  ImageText.swift
//  Trending
//
//  Created by Farid Mammadov on 06.01.23.
//

import SwiftUI

struct ImageText: View {
    
    let image: String
    let imageColor: Color
    let text: String
    
    var body: some View {
        HStack{
            Image(systemName: image)
                .resizable()
                .foregroundColor(imageColor)
                .frame(
                    width: Dimens.gu_1_25.rawValue,
                    height: Dimens.gu_1_25.rawValue
                )
            
            Text(text)
                .font(.caption2)
        }
    }
}

struct ImageText_Previews: PreviewProvider {
    static var previews: some View {
        ImageText(image: "star.fill", imageColor: .orange, text: "Stars")
    }
}
