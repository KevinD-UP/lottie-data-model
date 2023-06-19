package transformer

import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules

class KPTextTransformer {

    fun transformTexts(animation: KPLottieAnimation, animationRules: KPAnimationRules, texts: List<String>? = null)
            : KPLottieAnimation
    {
        var animationResult = animation.copy()
        if(texts == null) return animationResult

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val text = parseTextKey(texts, layerRule.textInd, layerRule.separator ?: " ")
                val textLayer = animation.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                if (text != null && textLayer != null) {
                    textLayer.t.d.k.firstOrNull()?.s?.t = text.handleMultiLine(layerRule.maxLines, layerRule.separator ?: " ")
                }
            }
        }
        return animationResult
    }

    private fun parseTextKey(texts: List<String>, textInds: List<Int>?, symbol: String): String? {

        var text = ""
        textInds?.forEach {
            text += texts[it] + symbol
        }

        return text.trim().ifEmpty { null }
    }

    private fun String.handleMultiLine(maxLines: Int, symbol: String): String {
        // remove excess break lines
        val text = this.replace(symbol, "\n")
        var breakLineCount = 0
        return text.map { c ->
            if (c == '\n') {
                breakLineCount++
                if (breakLineCount >= maxLines) {
                    return@map ' '
                }
            }
            return@map c
        }.joinToString("")
    }
}