package lottieAnimation.transformer

import lottieAnimation.Font
import lottieAnimation.FontList
import lottieAnimation.LottieAnimation
import lottieAnimation.layer.AnimatedTextDocument
import lottieAnimation.layer.Layer
import lottieAnimation.layer.LayerType
import lottieAnimation.layer.TextData
import lottieAnimation.layer.TextDocument
import lottieAnimation.layer.TextDocumentKeyframe
import lottieAnimation.layer.TextLayer
import lottieAnimation.rules.properties.AnimationRules

class FontTransformer {

    fun transformFonts(animation: LottieAnimation, animationRules: AnimationRules, fonts: Map<String, String>? = null)
        : LottieAnimation
    {
        var res = animation.copy()
        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = fonts?.let { parseFontKey(it, layerRule.fontKey) }
                val wantedLayer = animation.layers.find { it.ind == layerRule.ind && it.ty == LayerType.TEXT_LAYER }
                if (font != null && wantedLayer != null) {
                    when(wantedLayer) {
                        is TextLayer -> {
                            // Adding the font to the list of Font
                            val fontList: List<Font> = res.fonts!!.list!!.plus(font)

                            /* We need to do a deep copy of the object.
                             * It means that wherever we modify a "deep" value we also need to copy
                             * the parent of the value.
                             */
                            val layers: MutableList<Layer> = res.layers.toMutableList()
                            val indexOfWantedLayer: Int = layers.indexOf(wantedLayer)
                            val newTextDocument : TextDocument = wantedLayer.t.d.k[0].s.copy(f = font.fName)
                            val newKzero: TextDocumentKeyframe = wantedLayer.t.d.k[0].copy(s = newTextDocument)
                            val newK: MutableList<TextDocumentKeyframe> = wantedLayer.t.d.k.toMutableList()
                            newK[0] = newKzero
                            val newD: AnimatedTextDocument = wantedLayer.t.d.copy(k = newK)
                            val newTextData : TextData = wantedLayer.t.copy(d = newD)
                            val newLayer: TextLayer = wantedLayer.copy(t = newTextData)
                            layers[indexOfWantedLayer] = newLayer

                            // Create a copy of the animation with the updated fonts and layers
                            res = res.copy(fonts = FontList(fontList), layers = layers)
                        }
                        else -> {}
                    }
                }
            }
        }
        return res
    }

    private fun parseFontKey(fonts: Map<String, String>, fontKey: String): Font? {
        val fontPath = fonts[fontKey] ?: return null
        val fontName = fontPath.substringAfterLast("/").substringBefore(".")
        val splitFont = fontName.split('-')
        return Font(fName = fontName, fFamily = splitFont[0], fStyle = splitFont[1])
    }
}