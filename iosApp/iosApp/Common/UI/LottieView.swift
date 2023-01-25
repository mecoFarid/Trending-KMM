//
//  LottieView.swift
//  Trending
//
//  Created by Farid Mammadov on 09.01.23.
//

import SwiftUI
import Lottie

struct LottieView: UIViewRepresentable{

    let name: String

    init(name: String) {
        self.name = name
    }

    func makeUIView(context: Context) -> some UIView {
        let view = UIView(frame: .zero)
        let animationView = LottieAnimationView(name: name)
        animationView.contentMode = .scaleAspectFit
        animationView.play()
        animationView.loopMode = .loop

        view.addSubview(animationView)

        animationView.translatesAutoresizingMaskIntoConstraints = false
        animationView.heightAnchor.constraint(equalTo: view.heightAnchor).isActive = true
        animationView.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
        return view
    }

    func updateUIView(_ uiView: UIViewType, context: Context) {

    }
}

struct LottieView_Previews: PreviewProvider {
    static var previews: some View {
        LottieView(name: LottieAnimation.noData.rawValue)
    }
}