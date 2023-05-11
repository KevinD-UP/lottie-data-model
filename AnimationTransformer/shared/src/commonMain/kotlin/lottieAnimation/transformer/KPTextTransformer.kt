package lottieAnimation.transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.Font
import lottieAnimation.FontList
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.rules.properties.KPAnimationRules

class KPTextTransformer {

    fun transformTexts(animation: KPLottieAnimation, animationRules: KPAnimationRules, texts: List<String>? = null)
            : KPLottieAnimation
    {
        var res = animation.copy()
        if(texts == null) return res

        println("PHETS texts $texts")

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val text = parseTextKey(texts, layerRule.textInd)
                val wantedLayer = animation.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER }
                println("PHETS wantedLayer ${wantedLayer?.ind} - text $text")
                if (text != null && wantedLayer != null) {
                    when(wantedLayer) {
                        is KPTextLayer -> {
                            if (wantedLayer.t.d.k.isNotEmpty()) {
                                val layers: MutableList<KPLayer> = res.layers.toMutableList()
                                val indexOfWantedLayer: Int = layers.indexOf(wantedLayer)
                                val newTextDocument: KPTextDocument =
                                    wantedLayer.t.d.k[0].s.copy(t = text)
                                val newKzero: KPTextDocumentKeyframe =
                                    wantedLayer.t.d.k[0].copy(s = newTextDocument)
                                val newK: MutableList<KPTextDocumentKeyframe> =
                                    wantedLayer.t.d.k.toMutableList()
                                newK[0] = newKzero
                                val newD: KPAnimatedTextDocument = wantedLayer.t.d.copy(k = newK)
                                val newTextData: KPTextData = wantedLayer.t.copy(d = newD)
                                val newLayer: KPTextLayer = wantedLayer.copy(t = newTextData)
                                layers[indexOfWantedLayer] = newLayer

                                // Create a copy of the animation with the updated fonts and layers
                                res = res.copy(layers = layers)
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
        return res
    }

    private fun parseTextKey(texts: List<String>, textInds: List<Int>?): String? {
        val textInds = textInds ?: return null
        return textInds.firstOrNull()?.let {
            if (it < texts.count()) {
                texts[it]
            } else {
                null
            }
        }
    }
}