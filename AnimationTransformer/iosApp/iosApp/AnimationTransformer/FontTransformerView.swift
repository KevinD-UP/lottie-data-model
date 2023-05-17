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
                Menu {
                    Button {
                        viewModel.selectedFont = .arial
                    } label: {
                        Text("Arial")
                    }
                    Button {
                        viewModel.selectedFont = .chalkduster
                    } label: {
                        Text("Chalkduster")
                    }
                    Button {
                        viewModel.selectedFont = .papyrus
                    } label: {
                        Text("Papyrus")
                    }
                    Button {
                        viewModel.selectedFont = .zapfino
                    } label: {
                        Text("Zapfino")
                    }
                } label: {
                    Text("Font: \(viewModel.selectedFont.rawValue)")
                }
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

    enum FontTypeAnim: String {
        case arial = "Arial"
        case chalkduster = "Chalkduster"
        case papyrus = "Papyrus"
        case zapfino = "Zapfino"
    }

    private var jsonString: String?
    @Published var lottieAnimation: LottieAnimation?

    @Published var text1: String = "Titre en haut"
    @Published var text2: String = "Titre en bas"
    @Published var text3: String = "Sous-titre en haut"
    @Published var text4: String = "Sous-titre en bas"
    @Published var selectedFont: FontTypeAnim = .arial

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
        let functionsDelegate = FunctionsDelegateNative()
        let animationTransformer = KPAnimationTransformer(functionsDelegate: functionsDelegate)
        guard let result = animationTransformer.transform(
            lottieJsonString: animationJsonString,
            animationRulesJsonString: animationRulesJsonString,
            texts: texts,
            fonts: fonts
        ) else {
            print("Error: Cannot animation transformer the JSON file.")
            return
        }

        jsonString = result
        if let lottieAnimation = computeLottieAnimation() {
            holder.functionCaller.send(lottieAnimation)
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
