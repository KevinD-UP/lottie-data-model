//
//  FunctionsDelegateNative.swift
//  iosApp
//
//  Created by Phetsana Phommarinh on 12/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import UIKit

class FunctionsDelegateNative: KPAnimationTransformerFunctionsDelegate {
    func getAscent(text: String, fontName: String, fontSize: Double) -> Double {
        debugPrint("DEMOAPP text \(text) - fontName \(fontName) - fontSize \(fontSize)")
        let font = getFont(fontName: fontName, fontSize: fontSize)
        debugPrint("DEMOAPP getAscent = \(font.ascender)")
        //return -Double(font.ascender)
        return Double(font.ascender)
    }

    func getDescent(text: String, fontName: String, fontSize: Double) -> Double {
        debugPrint("DEMOAPP text \(text) - fontName \(fontName) - fontSize \(fontSize)")
        guard let font = UIFont(name: fontName, size: CGFloat(fontSize)) else {
            print("Error: Unable to load font: \(fontName)")
            return 0.0
        }

        let fontMetrics = UIFontMetrics(forTextStyle: .body)
        let descent = fontMetrics.scaledFont(for: font).descender
        return Double(abs(descent))
    }

    func getLastLineBottom(text: String, fontName: String, fontSize: Double) -> Double {
        debugPrint("DEMOAPP text \(text) - fontName \(fontName) - fontSize \(fontSize)")
        let font = getFont(fontName: fontName, fontSize: fontSize)
        debugPrint("DEMOAPP getLastLineBottom = \(font.descender)")
        return -Double(font.descender)
    }

    func getTextMeasureHeight(
        text: String,
        fontName: String, fontSize: Double,
        layerSize: [KotlinDouble]?, layerLineHeight: Double, layerTracking: Double
    ) -> Double {
        let uppercasedText = text.uppercased()
        let applyTextBoxText = applyTextBox(
            text: uppercasedText,
            fontName: fontName, fontSize: fontSize,
            layerSize: layerSize, layerLineHeight: layerLineHeight, layerTracking: layerTracking)
        let textLines = applyTextBoxText.split(separator: "\n")
        let lineHeight = layerLineHeight
        let ascent = -getAscent(text: text, fontName: fontName, fontSize: fontSize)
        let descent = getLastLineBottom(text: text, fontName: fontName, fontSize: fontSize)

        let result = ascent + descent + lineHeight * Double(textLines.count - 1)
        debugPrint("DEMOAPP getTextMeasureHeight result=\(result) ascent=\(ascent) descent=\(descent) lineHeight=\(lineHeight) - Double(textLines.count - 1)=\(Double(textLines.count - 1))")
        return result
    }

    func getTextLayerWidth(
        text: String,
        fontName: String, fontSize: Double
    ) -> Double {
        let textLines = text.split(separator: "\n")
        return textLines
            .map { String($0) }
            .map {
                getTextSize(text: $0, fontName: fontName, fontSize: fontSize)
            }
            .map { $0.width }
            .map { Double($0) }
            .max() ?? 0.0
    }
}

extension FunctionsDelegateNative {
    func getBounds(
        text: String, fontName: String, fontSize: Double
    ) -> CGRect {
        // Measure text
        let font = getFont(fontName: fontName, fontSize: fontSize)
        let uppercasedText = text.uppercased()
        let attributedText = NSAttributedString(string: uppercasedText, attributes: [NSAttributedString.Key.font: font])
        let bounds = attributedText.boundingRect(with: CGSize(width: CGFloat.greatestFiniteMagnitude, height: CGFloat.greatestFiniteMagnitude), options: [.usesLineFragmentOrigin, .usesFontLeading], context: nil)
        return bounds
    }

    func getFont(
        fontName: String, fontSize: Double
    ) -> UIFont {
        guard let font = UIFont(name: fontName, size: CGFloat(fontSize)) else {
            print("Error: Unable to load font: \(fontName)")
            return UIFont.preferredFont(forTextStyle: .body)
        }
        return font
    }

    func getTextBoxSize(size: [KotlinDouble]?) -> CGSize? {
        guard let size else { return nil }
        guard size.count == 2 else { return nil }
        return CGSize(width: size[0].doubleValue, height: size[1].doubleValue)
    }

    func applyTextBox(
        text: String,
        fontName: String, fontSize: Double,
        layerSize: [KotlinDouble]?, layerLineHeight: Double, layerTracking: Double
    ) -> String {
        var text = text
        guard let textBoxSize = getTextBoxSize(size: layerSize) else { return text }
        let font = getFont(fontName: fontName, fontSize: fontSize)
        let lineHeight = layerLineHeight
        let tracking = layerTracking
        let boxWidth = textBoxSize.width
        let boxHeight = textBoxSize.height

        let charCount = text.count
        var lastSpaceInd = -1
        var currentWidth: CGFloat = 0
        var i = 0
        while i < charCount {
            let charString = String(text[text.index(text.startIndex, offsetBy: i)])
            if charString == "\n" {
                lastSpaceInd = -1
                currentWidth = 0
                i += charString.count
                continue
            }
            if charString == " " {
                lastSpaceInd = i
            }
            let charWidth = (charString as NSString).size(withAttributes: [NSAttributedString.Key.font: font]).width
            currentWidth += charWidth
            if currentWidth > boxWidth {
                // No space left for first character
                if currentWidth == charWidth {
                    text = String(text.prefix(i))
                    break
                }
                if lastSpaceInd == -1 {
                    text = text.prefix(i) + "\n" + text.dropFirst(i)
                    i += 1
                } else {
                    text = text.prefix(lastSpaceInd) + "\n" + text.dropFirst(lastSpaceInd + 1)
                    i = lastSpaceInd
                }
                lastSpaceInd = -1
                currentWidth = 0
            } else {
                currentWidth += CGFloat(tracking)
                i += charString.count
            }
        }

        // Split full text in multiple lines
        let textLines = text.split(separator: "\n")
        let ascent = -font.ascender
        var boxHeightRemaining = CGFloat(boxHeight)
        if ascent < boxHeightRemaining {
            var textResult = String(textLines[0])
            boxHeightRemaining -= ascent
            for l in 1..<textLines.count {
                if CGFloat(lineHeight) < boxHeightRemaining {
                    textResult.append("\n" + textLines[l])
                    boxHeightRemaining -= CGFloat(lineHeight)
                } else {
                    break
                }
            }
            return textResult
        } else {
            return ""
        }
    }

    func getTextSize(
        text: String,
        fontName: String, fontSize: Double
    ) -> CGSize {
        let bounds = getBounds(text: text, fontName: fontName, fontSize: fontSize)
        return CGSize(width: bounds.width, height: bounds.height)
    }
}
