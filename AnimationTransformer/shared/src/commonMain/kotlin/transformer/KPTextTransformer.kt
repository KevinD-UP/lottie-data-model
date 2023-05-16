package transformer

import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.rules.properties.KPAnimationRules

class KPTextTransformer {

    fun transformTexts(animation: KPLottieAnimation, animationRules: KPAnimationRules, texts: List<String>? = null)
            : KPLottieAnimation
    {
        var animationResult = animation.copy()
        if(texts == null) return animationResult

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val text = parseTextKey(texts, layerRule.textInd)
                val textLayer = animation.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                if (text != null && textLayer != null) {
                    textLayer.t.d.k.firstOrNull()?.s?.t = text
                }
            }
        }
        return animationResult
    }

    private fun parseTextKey(texts: List<String>, textInds: List<Int>?): String? {
        return textInds?.firstOrNull()?.let {
            if (it < texts.count()) {
                texts[it]
            } else {
                null
            }
        }
    }
}