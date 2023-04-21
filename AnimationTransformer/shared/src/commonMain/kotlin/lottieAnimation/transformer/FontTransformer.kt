package lottieAnimation.transformer

import lottieAnimation.Font
import lottieAnimation.FontList
import lottieAnimation.LottieAnimation
import lottieAnimation.layer.Layer
import lottieAnimation.layer.TextData
import lottieAnimation.layer.TextDocument
import lottieAnimation.layer.TextLayer
import lottieAnimation.rules.properties.AnimationRules

class FontTransformer {

    fun transformFonts(animation: LottieAnimation, animationRules: AnimationRules)
        : LottieAnimation
    {
        var res = animation.copy()
        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = parseFontKey(layerRule.fontKey)
                val wantedLayer = animation.layers.find { it.ind == layerRule.ind }
                if (wantedLayer != null) {
                    when(wantedLayer) {
                        is TextLayer -> {
                            val fontList: List<Font> = res.fonts!!.list!!.plus(font)

                            val layers = res.layers.toMutableList()
                            val index = layers.indexOf(wantedLayer)
                            val newTextDocument : TextDocument = wantedLayer.t.d.k[0].s.copy(f = font.fName)
                            val newKzero = wantedLayer.t.d.k[0].copy(s = newTextDocument)
                            val newK = wantedLayer.t.d.k.toMutableList()
                            newK[0] = newKzero
                            val newD = wantedLayer.t.d.copy(k = newK)
                            val newTextData : TextData = wantedLayer.t.copy(d = newD)
                            val newLayer: TextLayer = wantedLayer.copy(t = newTextData)
                            layers[index] = newLayer

                            res = res.copy(fonts = FontList(fontList), layers = layers)
                        }
                        else -> {}
                    }
                }
            }
        }
        return res
    }

    private fun parseFontKey(fontKey: String): Font {
        return Font(fFamily = fontKey, fStyle = fontKey, fName = fontKey)
    }
}