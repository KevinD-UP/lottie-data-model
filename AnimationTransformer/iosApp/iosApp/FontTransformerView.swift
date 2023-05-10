//
//  FontTransformerView.swift
//  iosApp
//
//  Created by Phetsana Phommarinh on 05/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Lottie
import shared
import SwiftUI

struct FontTransformerView: View {

    enum FontTypeAnim: String {
        case arial = "Arial"
        case chalkduster = "Chalkduster"
        case papyrus = "Papyrus"
        case zapfino = "Zapfino"
    }

    @State var jsonString: String? = nil

    var body: some View {
        VStack {
            CustomAnimationLottieView(jsonString: $jsonString)
                .frame(width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.width * 9 / 16.0)

            Menu {
                Button {
                    fontChange(fonts: getFonts(type: .arial))
                } label: {
                    Text("Arial")
                }
                Button {
                    fontChange(fonts: getFonts(type: .chalkduster))
                } label: {
                    Text("Chalkduster")
                }
                Button {
                    fontChange(fonts: getFonts(type: .papyrus))
                } label: {
                    Text("Papyrus")
                }
                Button {
                    fontChange(fonts: getFonts(type: .zapfino))
                } label: {
                    Text("Zapfino")
                }
            } label: {
                Text("Font")
            }

        }
        .clipped()
        .navigationTitle("FontTransformerView")
        .onAppear {
            let familyNames = UIFont.familyNames

            for family in familyNames {
                print("Family name " + family)
                let fontNames = UIFont.fontNames(forFamilyName: family)

                for font in fontNames {
                    print("    Font name: " + font)
                }
            }
        }
    }

    private func getFonts(type: FontTypeAnim) -> [String: String] {
        switch type {
        case .arial:
            let fonts = [
                "textBoldItalic": "path-to-font/Arial-Black.ttf",
                "titleBlackItalic": "path-to-font/Arial-Bold.ttf",
                "textBlackItalic": "path-to-font/Arial-Black.ttf",
                "textBold": "path-to-font/Arial-Black.ttf",
                "titleBoldItalic": "path-to-font/Arial-Bold.ttf",
                "titleBold": "path-to-font/Arial-Bold.ttf",
                "textBlack": "path-to-font/Arial-Black.ttf",
                "text": "path-to-font/Arial-Black.ttf",
                "title": "path-to-font/Arial-Bold.ttf",
            ]
            return fonts
        case .chalkduster:
            let fonts = [
                "textBoldItalic": "path-to-font/Chalkduster.ttf",
                "titleBlackItalic": "path-to-font/Chalkduster.ttf",
                "textBlackItalic": "path-to-font/Chalkduster.ttf",
                "textBold": "path-to-font/Chalkduster.ttf",
                "titleBoldItalic": "path-to-font/Chalkduster.ttf",
                "titleBold": "path-to-font/Chalkduster.ttf",
                "textBlack": "path-to-font/Chalkduster.ttf",
                "text": "path-to-font/Chalkduster.ttf",
                "title": "path-to-font/Chalkduster.ttf",
            ]
            return fonts
        case .papyrus:
            let fonts = [
                "textBoldItalic": "path-to-font/Papyrus-Condensed.ttf",
                "titleBlackItalic": "path-to-font/Papyrus-Condensed.ttf",
                "textBlackItalic": "path-to-font/Papyrus-Condensed.ttf",
                "textBold": "path-to-font/Papyrus-Condensed.ttf",
                "titleBoldItalic": "path-to-font/Papyrus-Condensed.ttf",
                "titleBold": "path-to-font/Papyrus-Condensed.ttf",
                "textBlack": "path-to-font/Papyrus-Condensed.ttf",
                "text": "path-to-font/Papyrus.ttf",
                "title": "path-to-font/Papyrus-Condensed.ttf",
            ]
            return fonts
        case .zapfino:
            let fonts = [
                "textBoldItalic": "path-to-font/Zapfino.ttf",
                "titleBlackItalic": "path-to-font/Zapfino.ttf",
                "textBlackItalic": "path-to-font/Zapfino.ttf",
                "textBold": "path-to-font/Zapfino.ttf",
                "titleBoldItalic": "path-to-font/Zapfino.ttf",
                "titleBold": "path-to-font/Zapfino.ttf",
                "textBlack": "path-to-font/Zapfino.ttf",
                "text": "path-to-font/Zapfino.ttf",
                "title": "path-to-font/Zapfino.ttf",
            ]
            return fonts
        }
    }

    private func fontChange(fonts: [String: String]?) {
        guard let animationURL = Bundle.main.url(forResource: "animation", withExtension: "json"),
              let animationRulesURL = Bundle.main.url(forResource: "animation-rules", withExtension: "json") else {
            print("Error: Cannot find the JSON file.")
            return
        }

        guard let animationData = try? Data(contentsOf: animationURL),
              let animationRulesData = try? Data(contentsOf: animationRulesURL) else {
            print("Error: Cannot data the JSON file.")
            return
        }
        guard let animationJsonString = String(data: animationData, encoding: .utf8),
              let animationRulesJsonString = String(data: animationRulesData, encoding: .utf8) else {
            print("Error: Cannot string the JSON file.")
            return
        }

        let animationTransformer = KPAnimationTransformer()
        guard let result = animationTransformer.transform(
            lottieJsonString: animationJsonString,
            animationRulesJsonString: animationRulesJsonString,
            fonts: fonts) else {
            print("Error: Cannot animation transformer the JSON file.")
            return
        }

        self.jsonString = result
        debugPrint(result)
    }
}

struct FontTransformerView_Previews: PreviewProvider {
    static var previews: some View {
        FontTransformerView()
    }
}

struct CustomAnimationLottieView: UIViewRepresentable {
    @Binding var jsonString: String?

    init(jsonString: Binding<String?>) {
        _jsonString = jsonString
    }

    private var lottieView: LottieAnimationView = {
        let view = LottieAnimationView()
        view.loopMode = .loop
        view.backgroundColor = .orange
        view.translatesAutoresizingMaskIntoConstraints = false
        view.isUserInteractionEnabled = false
        return view
    }()


    func lottieAnimation() -> LottieAnimation? {
        guard let jsonString else {
            print("Error: Fallback default animation")
            return LottieAnimation.named("animation", bundle: Bundle.main)
        }
        guard let dictionary = jsonStringToDictionary(jsonString) else {
            print("Error: Cannot convert JSON string to Dictionary.")
            return nil
        }
        do {
            let animation = try LottieAnimation(dictionary: dictionary)
            return animation
        } catch {
            debugPrint("PHETS error = \(error.localizedDescription)")
        }

        return nil
    }

    func jsonStringToDictionary(_ jsonString: String) -> [String: Any]? {
        guard let jsonData = jsonString.data(using: .utf8) else {
            print("Error: Cannot convert JSON string to Data.")
            return nil
        }

        do {
            if let jsonObject = try JSONSerialization.jsonObject(with: jsonData, options: []) as? [String: Any] {
                return jsonObject
            } else {
                print("Error: Cannot convert JSON data to [String: Any] dictionary.")
                return nil
            }
        } catch {
            print("Error: Cannot deserialize JSON data. \(error.localizedDescription)")
            return nil
        }
    }

    func makeUIView(context: Context) -> UIView {
        let containerView = UIView()
        containerView.addSubview(lottieView)
        let constraints = [
            lottieView.leftAnchor.constraint(equalTo: containerView.leftAnchor),
            lottieView.topAnchor.constraint(equalTo: containerView.topAnchor),
            lottieView.rightAnchor.constraint(equalTo: containerView.rightAnchor),
            lottieView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor),
        ]
        NSLayoutConstraint.activate(constraints)
        return containerView
    }

    func updateUIView(_ webView: UIView, context: Context) {
        lottieView.animation = lottieAnimation()
        lottieView.play()
    }
}
