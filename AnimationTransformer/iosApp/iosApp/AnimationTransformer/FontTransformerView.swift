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
                animView()
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
    private func animView() -> some View {
        VStack {
            Menu {
                ForEach(FontTransformerViewModel.AnimationType.allCases, id: \.self) { anim in
                    Button {
                        viewModel.selectedAnim = anim
                    } label: {
                        Text(anim.rawValue)
                    }
                }
            } label: {
                Text("Animation: \(viewModel.selectedAnim.rawValue)")
            }
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

    enum AnimationType: String, CaseIterable {
        case algiersFord = "ALGIERS-FORD"
        case algiersPeugeot = "ALGIERS-PEUGEOT"
        case algiersPlane = "ALGIERS-PLANE"
        case algiersSimca = "ALGIERS-SIMCA"
        case baliFord = "BALI-FORD"
        case baliPeugeot = "BALI-PEUGEOT"
        case baliPlane = "BALI-PLANE"
        case berlinFord = "BERLIN-FORD"
        case berlinPeugeot = "BERLIN-PEUGEOT"
        case berlinPlane = "BERLIN-PLANE"
        case bogotaAustin = "BOGOTA-AUSTIN"
        case bogotaAventi = "BOGOTA-AVENTI"
        case bogotaDelorean = "BOGOTA-DELOREAN"
        case bogotaDodge = "BOGOTA-DODGE"
        case bogotaLincoln = "BOGOTA-LINCOLN"
        case bogotaMcLaren = "BOGOTA-MCLAREN"
        case bogotaRam = "BOGOTA-RAM"
        case bogotaRoush = "BOGOTA-ROUSH"
        case bogotaToyota = "BOGOTA-TOYOTA"
        case bogotaVector = "BOGOTA-VECTOR"
        case genevaFord = "GENEVA-FORD"
        case genevaPeugeot = "GENEVA-PEUGEOT"
        case genevaPlane = "GENEVA-PLANE"
        case losAngelesFord = "LOS_ANGELES-FORD"
        case losAngelesPeugeot = "LOS_ANGELES-PEUGEOT"
        case losAngelesPlane = "LOS_ANGELES-PLANE"
        case losAngelesSimca = "LOS_ANGELES-SIMCA"
        case parisButterfly = "PARIS-BUTTERFLY"
        case parisFox = "PARIS-FOX"
        case parisOwl = "PARIS-OWL"
        case seattleFord = "SEATTLE-FORD"
        case seattlePeugeot = "SEATTLE-PEUGEOT"
        case seattlePlane = "SEATTLE-PLANE"
    }

    enum FontTypeAnim: String, CaseIterable {
        case arial = "Arial"
        case chalkduster = "Chalkduster"
        case lato = "Lato"
        case papyrus = "Papyrus"
        case zapfino = "Zapfino"
    }

    enum ColorThemeType: String, CaseIterable {
        case accor = "Accor"
        case bts = "BTS"
        case blockchain = "Blockchain"
    }

    private var jsonString: String?
    @Published var lottieAnimation: LottieAnimation?

    @Published var text1: String = "Titre en haut"
    @Published var text2: String = "Titre en bas"
    @Published var text3: String = "Sous-titre en haut"
    @Published var text4: String = "Sous-titre en bas"
    @Published var selectedAnim: AnimationType = .baliPlane
    @Published var selectedFont: FontTypeAnim = .lato
    @Published var selectedThemeColor: ColorThemeType = .accor

    func updateAnimation() {
        guard let animationURL = Bundle.main.url(forResource: selectedAnim.rawValue, withExtension: "json"),
              let animationRulesURL = Bundle.main.url(forResource: "\(selectedAnim.rawValue)-rules", withExtension: "json") else {
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
        let functionsDelegate = FunctionsDelegateNative()
        let animationTransformer = KPAnimationTransformer(functionsDelegate: functionsDelegate)
        let colors = getColors(animation: selectedAnim, theme: selectedThemeColor)
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

    private func getColors(animation: AnimationType, theme: ColorThemeType) -> [String: String] {
        guard let themeName = animation.rawValue.split(separator: "-").first?.lowercased() else {
            return [:]
        }
        switch theme {
        case .accor:
            return colorSetAccor()[themeName] ?? [:]
        case .bts:
            return colorSetBTS()[themeName] ?? [:]
        case .blockchain:
            return colorSetBlockchain()[themeName] ?? [:]
        }
    }

    private func getFonts(type: FontTypeAnim) -> [String: String] {
        switch type {
        case .arial:
            let fonts = [
                "textBoldItalic": "path-to-font/Arial-BoldMT.ttf",
                "titleBlackItalic": "path-to-font/Arial-BoldMT.ttf",
                "textBlackItalic": "path-to-font/Arial-BoldMT.ttf",
                "textBold": "path-to-font/Arial-BoldMT.ttf",
                "titleBoldItalic": "path-to-font/Arial-BoldMT.ttf",
                "titleBold": "path-to-font/Arial-BoldMT.ttf",
                "textBlack": "path-to-font/Arial-BoldMT.ttf",
                "text": "path-to-font/ArialMT.ttf",
                "title": "path-to-font/Arial-BoldMT.ttf",
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
        case .lato:
            let fonts = [
                "textBoldItalic": "path-to-font/Lato-Bold.ttf",
                "titleBlackItalic": "path-to-font/Lato-Bold.ttf",
                "textBlackItalic": "path-to-font/Lato-Bold.ttf",
                "textBold": "path-to-font/Lato-Bold.ttf",
                "titleBoldItalic": "path-to-font/Lato-Bold.ttf",
                "titleBold": "path-to-font/Lato-Bold.ttf",
                "textBlack": "path-to-font/Lato-Bold.ttf",
                "text": "path-to-font/Lato-Regular.ttf",
                "title": "path-to-font/Lato-Regular.ttf",
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

extension FontTransformerViewModel {
    private func colorSetAccor() -> [String: [String: String]] {
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
        return [
            "bogota": bogotaColors,
            "algiers": algiersColors,
            "bali": baliColors,
            "paris": baliColors,
            "geneva": genevaColors,
            "los_angeles": losAngelesColors,
            "seattle": seattleColors,
            "berlin": berlinColors
        ]
    }

    private func colorSetBTS() -> [String: [String: String]] {
        let bogotaColors = [
            "coverTextOpacity": "0.50",
            "shapeShadow": "#000000",
            "negativeBarBottom": "#FFFFFF",
            "coverText": "#FFFFFF",
            "backgroundOpacity": "1.00",
            "titleBlinking": "#00e183",
            "source": "#00e183",
            "title": "#FFFFFF",
            "positiveBarTop": "#00e183",
            "positiveBarBottom": "#00e183",
            "shapeNeutral": "#FFFFFF",
            "cta": "#FFFFFF",
            "negativeBarTop": "#FFFFFF",
            "ctaTitle": "#FFFFFF",
            "transparentBackground": "#000000",
            "maskGradientBottom": "#00e183",
            "maskGradientTop": "#00e183",
            "text": "#FFFFFF",
            "backgroundBarOpacity": "0.20",
            "textShadow": "#000000",
            "figure": "#FFFFFF",
            "shapeCosmeticOpacity": "0.70",
            "titleBottom": "#FFFFFF",
            "shapeCosmetic": "#FFFFFF",
            "chartEmpty": "#FFFFFF",
            "background": "#F5D809",
            "shapeSecondary": "#F5D809",
            "textShadowOpacity": "0.70",
            "chartFill": "#00e183",
            "shapeMain": "#00e183",
            "sourceNeutral": "#FFFFFF",
            "figureBlink": "#00e183",
            "shapeShadowOpacity": "0.70",
            "ctaTitleBlinking": "#00e183"
        ]
        let berlinColors = [
            "gradientTop": "#00e183",
            "neutralGradientTop": "#FFFFFF",
            "titleGradientTop": "#00e183",
            "textBackgroundOpacity": "1.00",
            "shapeSecond": "#F5D809",
            "neutralGradientBottom": "#000000",
            "shapeNeutral": "#FFFFFF",
            "background": "#F5D809",
            "titleGradientBottom": "#F5D809",
            "titleBackgroundOpacity": "0.80",
            "slideBackground": "#FFFFFF",
            "maskGradientBottom": "#F5D809",
            "maskGradientTop": "#00e183",
            "textBackground": "#F5D809",
            "gradientBottom": "#F5D809",
            "text": "#FFFFFF",
            "shapeMain": "#00e183",
            "titleBackground": "#000000"
        ]
        let genevaColors = [
            "shape": "#FFFFFF",
            "shadow": "#000000",
            "background": "#F5D809",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.50",
            "maskGradientBottom": "#00e183",
            "maskGradientTop": "#00e183",
            "text": "#FFFFFF",
            "shadowOpacity": "0.70",
            "titleBackground": "#000000"
        ]
        let losAngelesColors = [
            "shadow": "#000000",
            "slideTextStrong": "#00e183",
            "subtitleBottomText": "#FFFFFF",
            "subtitleTopText": "#FFFFFF",
            "shadowOpacity": "0.50",
            "background": "#F5D809",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "slideBackground": "#FFFFFF",
            "floatingBackground": "#F5D809",
            "slideText": "#F5D809",
            "maskGradientTop": "#00e183",
            "maskGradientBottom": "#F5D809",
            "text": "#FFFFFF",
            "floatingText": "#FFFFFF",
            "subtitleBackground": "#F5D809",
            "titleBackground": "#000000"
        ]
        let seattleColors = [
            "subtitleBottomBackground": "#F5D809",
            "shape": "#F5D809",
            "slideFullBackground": "#00e183",
            "slideSplitBottomText": "#00e183",
            "subtitleBottomText": "#FFFFFF",
            "slideSplitBottomBackground": "#FFFFFF",
            "subtitleTopText": "#F5D809",
            "background": "#F5D809",
            "slideSplitTopText": "#FFFFFF",
            "slideFullText": "#FFFFFF",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.80",
            "floatingBackground": "#FFFFFF",
            "maskGradientBottom": "#F5D809",
            "maskGradientTop": "#F5D809",
            "text": "#FFFFFF",
            "slideSplitTopBackground": "#00e183",
            "floatingText": "#F5D809",
            "subtitleTopBackground": "#FFFFFF",
            "titleBackground": "#000000"
        ]
        let baliColors = [
            "shape": "#00e183",
            "background": "#F5D809",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "backgroundOpacity": "0.50",
            "maskGradientBottom": "#F5D809",
            "maskGradientTop": "#F5D809",
            "text": "#FFFFFF",
            "titleBackground": "#000000"
        ]
        let algiersColors = [
            "subtitleBottomBackground": "#00e183",
            "slideSplitBottomText": "#FFFFFF",
            "subtitleBottomText": "#FFFFFF",
            "gradientRight": "#F5D809",
            "slideSplitBottomBackground": "#F5D809",
            "subtitleTopText": "#FFFFFF",
            "gradientText": "#FFFFFF",
            "background": "#F5D809",
            "slideSplitTopText": "#FFFFFF",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "maskGradientBottom": "#00e183",
            "maskGradientTop": "#00e183",
            "text": "#FFFFFF",
            "gradientLeft": "#F5D809",
            "slideSplitTopBackground": "#00e183",
            "subtitleOpacity": "0.80",
            "subtitleTopBackground": "#F5D809",
            "titleBackground": "#000000"
        ]
        return [
            "bogota": bogotaColors,
            "algiers": algiersColors,
            "bali": baliColors,
            "paris": baliColors,
            "geneva": genevaColors,
            "los_angeles": losAngelesColors,
            "seattle": seattleColors,
            "berlin": berlinColors
        ]
    }

    private func colorSetBlockchain() -> [String: [String: String]] {
        let bogotaColors = [
            "coverTextOpacity": "0.50",
            "shapeShadow": "#000000",
            "negativeBarBottom": "#FFFFFF",
            "coverText": "#FFFFFF",
            "backgroundOpacity": "1.00",
            "titleBlinking": "#00AEE6",
            "source": "#00AEE6",
            "title": "#FFFFFF",
            "positiveBarTop": "#00AEE6",
            "positiveBarBottom": "#00AEE6",
            "shapeNeutral": "#FFFFFF",
            "cta": "#FFFFFF",
            "negativeBarTop": "#FFFFFF",
            "ctaTitle": "#FFFFFF",
            "transparentBackground": "#000000",
            "maskGradientBottom": "#00AEE6",
            "maskGradientTop": "#00AEE6",
            "text": "#FFFFFF",
            "backgroundBarOpacity": "0.20",
            "textShadow": "#000000",
            "figure": "#FFFFFF",
            "shapeCosmeticOpacity": "0.70",
            "titleBottom": "#FFFFFF",
            "shapeCosmetic": "#FFFFFF",
            "chartEmpty": "#FFFFFF",
            "background": "#123962",
            "shapeSecondary": "#123962",
            "textShadowOpacity": "0.70",
            "chartFill": "#00AEE6",
            "shapeMain": "#00AEE6",
            "sourceNeutral": "#FFFFFF",
            "figureBlink": "#00AEE6",
            "shapeShadowOpacity": "0.70",
            "ctaTitleBlinking": "#00AEE6"
        ]
        let berlinColors = [
            "gradientTop": "#00AEE6",
            "neutralGradientTop": "#FFFFFF",
            "titleGradientTop": "#00AEE6",
            "textBackgroundOpacity": "1.00",
            "shapeSecond": "#799EB2",
            "neutralGradientBottom": "#000000",
            "shapeNeutral": "#FFFFFF",
            "titleGradientBottom": "#799EB2",
            "titleBackgroundOpacity": "0.80",
            "slideBackground": "#FFFFFF",
            "maskGradientBottom": "#799EB2",
            "maskGradientTop": "#00AEE6",
            "textBackground": "#123962",
            "gradientBottom": "#799EB2",
            "text": "#FFFFFF",
            "shapeMain": "#00AEE6",
            "titleBackground": "#000000"
        ]
        let genevaColors = [
            "shape": "#FFFFFF",
            "shadow": "#000000",
            "background": "#123962",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.50",
            "maskGradientBottom": "#00AEE6",
            "maskGradientTop": "#00AEE6",
            "text": "#FFFFFF",
            "shadowOpacity": "0.70",
            "titleBackground": "#000000"
        ]
        let losAngelesColors = [
            "shadow": "#000000",
            "slideTextStrong": "#00AEE6",
            "subtitleBottomText": "#FFFFFF",
            "subtitleTopText": "#FFFFFF",
            "shadowOpacity": "0.50",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "slideBackground": "#FFFFFF",
            "floatingBackground": "#123962",
            "slideText": "#123962",
            "maskGradientTop": "#00AEE6",
            "maskGradientBottom": "#799EB2",
            "floatingText": "#FFFFFF",
            "subtitleBackground": "#123962",
            "titleBackground": "#000000"
        ]
        let seattleColors = [
            "subtitleBottomBackground": "#123962",
            "shape": "#123962",
            "slideFullBackground": "#00AEE6",
            "slideSplitBottomText": "#00AEE6",
            "subtitleBottomText": "#FFFFFF",
            "slideSplitBottomBackground": "#FFFFFF",
            "subtitleTopText": "#123962",
            "slideSplitTopText": "#FFFFFF",
            "slideFullText": "#FFFFFF",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.80",
            "floatingBackground": "#FFFFFF",
            "maskGradientBottom": "#799EB2",
            "maskGradientTop": "#799EB2",
            "slideSplitTopBackground": "#00AEE6",
            "floatingText": "#123962",
            "subtitleTopBackground": "#FFFFFF",
            "titleBackground": "#000000"
        ]
        let baliColors = [
            "shape": "#00AEE6",
            "background": "#123962",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "backgroundOpacity": "0.50",
            "maskGradientBottom": "#123962",
            "maskGradientTop": "#123962",
            "text": "#FFFFFF",
            "titleBackground": "#000000"
        ]
        let algiersColors = [
            "subtitleBottomBackground": "#00AEE6",
            "slideSplitBottomText": "#FFFFFF",
            "subtitleBottomText": "#FFFFFF",
            "gradientRight": "#123962",
            "slideSplitBottomBackground": "#123962",
            "subtitleTopText": "#FFFFFF",
            "gradientText": "#FFFFFF",
            "slideSplitTopText": "#FFFFFF",
            "titleText": "#FFFFFF",
            "titleBackgroundOpacity": "0.70",
            "maskGradientBottom": "#00AEE6",
            "maskGradientTop": "#00AEE6",
            "gradientLeft": "#123962",
            "slideSplitTopBackground": "#00AEE6",
            "subtitleOpacity": "0.80",
            "subtitleTopBackground": "#123962",
            "titleBackground": "#000000"
        ]
        return [
            "bogota": bogotaColors,
            "algiers": algiersColors,
            "bali": baliColors,
            "paris": baliColors,
            "geneva": genevaColors,
            "los_angeles": losAngelesColors,
            "seattle": seattleColors,
            "berlin": berlinColors
        ]
    }
}
