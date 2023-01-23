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
                .foregroundColor(imageColor)
                .imageScale(.small)
            
            Text(text)
                .font(.caption)
        }
    }
}

struct ImageText_Previews: PreviewProvider {
    static var previews: some View {
        ImageText(image: "star.fill", imageColor: .orange, text: "Stars")
    }
}
