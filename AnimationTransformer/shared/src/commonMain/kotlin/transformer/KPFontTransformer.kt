package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.Font
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules

class KPFontTransformer {

    fun transformFonts(animation: KPLottieAnimation, animationRules: KPAnimationRules, fonts: Map<String, String>? = null)
        : KPLottieAnimation
    {
        var animationResult = animation.copy()
        if(fonts == null) return animationResult

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = parseFontKey(fonts, layerRule.fontKey)
                val textLayer = animation.layers.find { it.nm == layerRule.layerName && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                println("textLayer = $textLayer")
                println("font = $font")
                if (font != null && textLayer != null) {
                    textLayer.t.d.k.firstOrNull()?.s?.f = font.fName
                }
            }
        }
        return animationResult
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