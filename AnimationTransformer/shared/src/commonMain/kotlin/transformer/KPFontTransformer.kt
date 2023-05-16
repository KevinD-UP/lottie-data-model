package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.Font
import lottieAnimation.FontList
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPAnimatedTextDocument
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPTextData
import lottieAnimation.layer.KPTextDocument
import lottieAnimation.layer.KPTextDocumentKeyframe
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules

class KPFontTransformer {

    fun transformFonts(animation: KPLottieAnimation, animationRules: KPAnimationRules, fonts: Map<String, String>? = null)
        : KPLottieAnimation
    {
        var res = animation.copy()
        if(fonts == null) return res

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = parseFontKey(fonts, layerRule.fontKey)
                val wantedLayer = animation.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER }

                if (font != null && wantedLayer != null) {
                    when(wantedLayer) {
                        is KPTextLayer -> {
                            if (wantedLayer.t.d.k.isNotEmpty()) {
                                // Adding the font to the list of Font
                                val fontList = res.fonts?.list?.plus(font) ?: mutableListOf(font)

                                /* We need to do a deep copy of the object.
                                 * It means that wherever we modify a "deep" value we also need to copy
                                 * the parent of the value.
                                 */
                                val layers: MutableList<KPLayer> = res.layers.toMutableList()
                                val indexOfWantedLayer: Int = layers.indexOf(wantedLayer)
                                val newTextDocument: KPTextDocument =
                                    wantedLayer.t.d.k[0].s.copy(f = font.fName)
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
                                res = res.copy(fonts = FontList(fontList), layers = layers)
                            }
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
        // TODO: To compute ascent
        if (splitFont.size == 1) return Font(fName = fontName, fFamily = splitFont[0], fStyle = splitFont[0], ascent = JsonPrimitive(value = 75.0))
        if (splitFont.size != 2) return null
        val fontFamily = splitFont[0]
        val fontStyle = splitFont[1]
        // TODO: To compute ascent
        return Font(fName = fontName, fFamily = fontFamily, fStyle = fontStyle, ascent = JsonPrimitive(value = 75.0))
    }
}