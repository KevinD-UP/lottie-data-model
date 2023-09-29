package transformer

import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules

class KPTextTransformer {

    fun transformTexts(animation: KPLottieAnimation, animationRules: KPAnimationRules?, texts: List<String>? = null)
            : KPLottieAnimation
    {
        var animationResult = animation.copy()
        if (texts == null) return animationResult
        if (animationRules == null) {
            val text = texts[0]
            val textLayer = animation.layers.find { it.nm == "text1" && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
            val textLayerTemplate = animation.layers.find { it.nm == "text1_template" && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
            if (textLayer != null && textLayerTemplate != null) {
                textLayer.t.d.k.firstOrNull()?.s?.t = text
            }
            return animationResult
        }

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val text = parseTextKey(texts, layerRule.textInd, layerRule.separator ?: " ")
                val textLayer = animation.layers.find { it.nm == layerRule.layerName && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                if (text != null && textLayer != null) {
                    if(layerRule.maxLines == 1) {
                        textLayer.t.d.k.firstOrNull()?.s?.t = text
                    } else {
                        textLayer.t.d.k.firstOrNull()?.s?.t = text.handleMultiLine(layerRule.maxLines)
                    }
                }
            }
        }
        return animationResult
    }

    private fun parseTextKey(texts: List<String>, textInds: List<Int>?, separator: String): String? {
        return textInds?.joinToString(separator = separator) { index ->
            texts.getOrNull(index) ?: ""
        }?.trim().takeIf { it?.isNotEmpty() ?: false }
    }

    private fun String.handleMultiLine(maxLines: Int): String {
        return this.replace("\r", "\n")
            .split("\n")
            .take(maxLines)
            .joinToString(separator = "\n")
    }
}