//
//  Test.swift
//  iosApp
//
//  Created by Farid Mammadov on 10.02.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Text {
    func wideLeading() -> some View {
        self.frame(maxWidth: .infinity, alignment: .leading)
    }
}

struct ContentView: View {

    var body: some View {
        VStack(spacing: 20) {
            topHstack
                .background(Color.gray)
            bottomHstack
                .background(Color.gray)
        }
        .padding(.horizontal, 20)
    }

    var topHstack: some View {
        HStack {
            Text("1 top").wideLeading()
            Text("2 top").wideLeading()
            Text("3 top").wideLeading()
        }
    }

    var bottomHstack: some View {
        HStack {
            Text("1 bottom").wideLeading()
            Text("2 bottom3 bottom3 bottom3 bottom3 bottom3 bottom3 bottom").wideLeading()
            Text("3 bottom3bottom3bottom3bottom").wideLeading()
        }
    }
}


struct Test_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
