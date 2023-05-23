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

    @StateObject var viewModel = FontTransformerViewModel()

    var body: some View {
        ScrollView {
            VStack {
                textView()
                fontView()
                colorView()
                CustomAnimationLottieView(holder: viewModel.holder)
                    .frame(width: UIScreen.main.bounds.size.width, height: UIScreen.main.bounds.size.width * 9 / 16.0)
                Button {
                    viewModel.updateAnimation()
                } label: {
                    Text("Update anim")
                }

                Spacer()

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

    @ViewBuilder
    private func textView() -> some View {
        VStack {
            HStack {
                TextField("Text1", text: $viewModel.text1)
                    .padding(.vertical, 10)
                    .padding(.horizontal, 15)
                    .background(Color.gray.opacity(0.2))
                    .cornerRadius(5)

                if !viewModel.text1.isEmpty {
                    Button(action: {
                        viewModel.text1 = ""
                    }) {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundColor(.gray)
                            .padding(5)
                    }
                }
            }
            HStack {
                TextField("Text2", text: $viewModel.text2)
                    .padding(.vertical, 10)
                    .padding(.horizontal, 15)
                    .background(Color.gray.opacity(0.2))
                    .cornerRadius(5)

                if !viewModel.text2.isEmpty {
                    Button(action: {
                        viewModel.text2 = ""
                    }) {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundColor(.gray)
                            .padding(5)
                    }
                }
            }
            HStack {
                TextField("Text3", text: $viewModel.text3)
                    .padding(.vertical, 10)
                    .padding(.horizontal, 15)
                    .background(Color.gray.opacity(0.2))
                    .cornerRadius(5)

                if !viewModel.text3.isEmpty {
                    Button(action: {
                        viewModel.text3 = ""
                    }) {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundColor(.gray)
                            .padding(5)
                    }
                }
            }
            HStack {
                TextField("Text4", text: $viewModel.text4)
                    .padding(.vertical, 10)
                    .padding(.horizontal, 15)
                    .background(Color.gray.opacity(0.2))
                    .cornerRadius(5)

                if !viewModel.text4.isEmpty {
                    Button(action: {
                        viewModel.text4 = ""
                    }) {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundColor(.gray)
                            .padding(5)
                    }
                }
            }
        }
    }

    @ViewBuilder
    private func fontView() -> some View {
        Menu {
            ForEach(FontTransformerViewModel.FontTypeAnim.allCases, id: \.self) { font in
                Button {
                    viewModel.selectedFont = font
                } label: {
                    Text(font.rawValue)
                }
            }
        } label: {
            Text("Font: \(viewModel.selectedFont.rawValue)")
        }
    }

    @ViewBuilder
    private func colorView() -> some View {
        VStack {
            Menu {
                ForEach(FontTransformerViewModel.ColorThemeType.allCases, id: \.self) { theme in
                    Button {
                        viewModel.selectedThemeColor = theme
                    } label: {
                        Text(theme.rawValue)
                    }
                }
            } label: {
                Text("Theme: \(viewModel.selectedThemeColor.rawValue)")
            }
        }
    }
}

struct FontTransformerView_Previews: PreviewProvider {
    static var previews: some View {
        FontTransformerView()
    }
}

class AnimatorHolder {
    var functionCaller = PassthroughSubject<LottieAnimation, Never>()
    var cancellables : [AnyCancellable] = []
}

struct CustomAnimationLottieView: UIViewRepresentable {
    let holder: AnimatorHolder

    init(holder: AnimatorHolder) {
        self.holder = holder
    }

    private var lottieView: LottieAnimationView = {
        let view = LottieAnimationView()
        view.loopMode = .loop
        view.backgroundColor = .orange
        view.translatesAutoresizingMaskIntoConstraints = false
        view.isUserInteractionEnabled = false
        return view
    }()

    func makeUIView(context: Context) -> UIView {
        let containerView = UIView()
        setupLottieView(containerView: containerView)
        return containerView
    }

    func setupLottieView(containerView: UIView, animation: LottieAnimation? = nil) {
        containerView.subviews.forEach { $0.removeFromSuperview() }
        let lottieView = LottieAnimationView()
        lottieView.loopMode = .loop
        lottieView.backgroundColor = .orange
        lottieView.translatesAutoresizingMaskIntoConstraints = false
        lottieView.isUserInteractionEnabled = false
        lottieView.animation = animation
        lottieView.play()
        containerView.addSubview(lottieView)
        let constraints = [
            lottieView.leftAnchor.constraint(equalTo: containerView.leftAnchor),
            lottieView.topAnchor.constraint(equalTo: containerView.topAnchor),
            lottieView.rightAnchor.constraint(equalTo: containerView.rightAnchor),
            lottieView.bottomAnchor.constraint(equalTo: containerView.bottomAnchor),
        ]
        NSLayoutConstraint.activate(constraints)
    }

    func updateUIView(_ webView: UIView, context: Context) {
        holder.cancellables = []
        holder.functionCaller.sink { lottieAnimation in
            setupLottieView(containerView: webView, animation: lottieAnimation)
//            lottieView.animation = lottieAnimation
//            lottieView.play()
        }
        .store(in: &holder.cancellables)
    }
}

@MainActor
class FontTransformerViewModel: ObservableObject {

    let holder = AnimatorHolder()

    enum FontTypeAnim: String, CaseIterable {
        case arial = "Arial"
        case chalkduster = "Chalkduster"
        case papyrus = "Papyrus"
        case zapfino = "Zapfino"
    }

    enum ColorThemeType: String, CaseIterable {
        case bali
        case algiers
        case seattle
        case berlin
        case losAngeles
        case bogota
        case geneva
    }

    private var jsonString: String?
    @Published var lottieAnimation: LottieAnimation?

    @Published var text1: String = "Text 1"
    @Published var text2: String = "Text 2"
    @Published var text3: String = "Text 3"
    @Published var text4: String = "Text 4"
    @Published var selectedFont: FontTypeAnim = .arial
    @Published var selectedThemeColor: ColorThemeType = .bali

    func updateAnimation() {
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
        let texts = [text1, text2, text3, text4]
        let fonts = getFonts(type: selectedFont)
        let colors = getColors(theme: selectedThemeColor)
        let animationTransformer = KPAnimationTransformer()
        guard let result = animationTransformer.transform(
            lottieJsonString: animationJsonString,
            animationRulesJsonString: animationRulesJsonString,
            texts: texts,
            fonts: fonts,
            colors: colors
        ) else {
            print("Error: Cannot animation transformer the JSON file.")
            return
        }

        jsonString = result
        if let lottieAnimation = computeLottieAnimation() {
            holder.functionCaller.send(lottieAnimation)
        }
    }

    private func getColors(theme: ColorThemeType) -> [String: String] {
        let bogotaColors = [
            "shapeNeutral": "#342EEA",
            "figure": "#908080",
            "shapeShadow": "#716B6B",
            "background": "#D55656",
            "shapeSecondary": "#CBE60A",
            "backgroundOpacity": "0.3",
            "text": "#FFFFFF",
            "shapeMain": "#FB6B00",
            "shapeShadowOpacity": "0.80"
        ]
        let berlinColors = [
            "gradientTop": "#FFD280",
            "neutralGradientTop": "#ffffff",
            "titleGradientTop": "#80FFE6",
            "textBackgroundOpacity": "0.7",
            "neutralGradientBottom": "#000000",
            "shapeSecond": "#050033",
            "shapeNeutral": "#ffffff",
            "background": "#D3A86A",
            "titleGradientBottom": "#D3A86A",
            "slideBackground": "#ffffff",
            "titleBackgroundOpacity": "0.7",
            "textBackground": "#050033",
            "maskGradientBottom": "#D3A86A",
            "maskGradientTop": "#FFD280",
            "text": "#ffffff",
            "gradientBottom": "#D3A86A",
            "shapeMain": "#D3A86A",
            "titleBackground": "#000000"
        ]
        let genevaColors = [
            "container": "#002b41",
            "shadow": "#000000",
            "shape": "#ffffff",
            "background": "#050033",
            "line": "#e6a500",
            "titleText": "#ffffff",
            "maskGradientBottom": "#D3A86A",
            "maskGradientTop": "#FFD280",
            "text": "#ffffff",
            "shadowOpacity": "0.7",
            "titleBackground": "#000000"
        ]
        let losAngelesColors = [
            "shadow": "#FFFF05",
            "slideTextStrong": "#D3A86A",
            "textStrong": "#e6a500",
            "subtitleBottomText": "#ffffff",
            "shadowOpacity": "1",
            "subtitleTopText": "#ffffff",
            "containerStrong": "#e6a500",
            "background": "#D3A86A",
            "titleText": "#ffffff",
            "slideBackground": "#ffffff",
            "slideText": "#050033",
            "floatingBackground": "#EEECF2",
            "maskGradientBottom": "#D3A86A",
            "maskGradientTop": "#FFD280",
            "floatingText": "#ffffff",
            "text": "#002b41",
            "subtitleBackground": "#050033",
            "titleBackground": "#000000"
        ]
        let seattleColors = [
            "subtitleBottomBackground": "#050033",
            "shape": "#050033",
            "slideFullBackground": "#D3A86A",
            "slideSplitBottomText": "#D3A86A",
            "subtitleBottomText": "#ffffff",
            "main": "#e6a500",
            "slideSplitBottomBackground": "#ffffff",
            "subtitleTopText": "#050033",
            "second": "#002b41",
            "background": "#D3A86A",
            "slideSplitTopText": "#ffffff",
            "slideFullText": "#ffffff",
            "titleText": "#ffffff",
            "floatingBackground": "#ffffff",
            "maskGradientTop": "#FFD280",
            "maskGradientBottom": "#D3A86A",
            "text": "#FFFFFF",
            "slideSplitTopBackground": "#D3A86A",
            "floatingText": "#050033",
            "titleBackground": "#000000",
            "subtitleTopBackground": "#ffffff"
        ]
        let baliColors = [
            "container": "#002b41",
            "shape": "#D3A86A",
            "line": "#e6a500",
            "backgroundOpacity": "0.5",
            "background": "#050033",
            "titleText": "#ffffff",
            "titleBackgroundOpacity": "0.85",
            "maskGradientBottom": "#D3A86A",
            "maskGradientTop": "#FFD280",
            "text": "#ffffff",
            "subtitleOpacity": "0.85",
            "titleBackground": "#000000"
        ]
        let algiersColors = [
            "subtitleBottomBackground": "#D3A86A",
            "containerBottom": "#e6a500",
            "slideSplitBottomText": "#ffffff",
            "subtitleBottomText": "#ffffff",
            "slideSplitBottomBackground": "#050033",
            "gradientRight": "#050033",
            "subtitleTopText": "#ffffff",
            "gradientText": "#ffffff",
            "containerTop": "#002b41",
            "slideSplitTopText": "#ffffff",
            "background": "#D3A86A",
            "titleText": "#ffffff",
            "titleBackgroundOpacity": "0.8",
            "maskGradientTop": "#FFD280",
            "maskGradientBottom": "#D3A86A",
            "text": "#FFFFFF",
            "gradientLeft": "#050033",
            "slideSplitTopBackground": "#D3A86A",
            "subtitleOpacity": "0.8",
            "titleBackground": "#d3a86a",
            "subtitleTopBackground": "#4030ca"
        ]
        switch theme {
        case .bali:
            return baliColors
        case .algiers:
            return algiersColors
        case .seattle:
            return seattleColors
        case .berlin:
            return berlinColors
        case .losAngeles:
            return losAngelesColors
        case .bogota:
            return bogotaColors
        case .geneva:
            return genevaColors
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

    func computeLottieAnimation() -> LottieAnimation? {
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
            debugPrint("error = \(error.localizedDescription)")
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
}
