package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.Font
import lottieAnimation.layer.properties.KPMultiDimensionalPrimitive

import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPNullLayer
import lottieAnimation.layer.KPTextJustify
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.rules.properties.KPAnimationRules

data class FontModel(
    val fontName: String?,
    val textAlign: Int?
)

class KPFontTransformer(
    private val delegate: KPAnimationTransformerFunctionsDelegate
){

    fun transformFonts(
        animation: KPLottieAnimation,
        animationRules: KPAnimationRules,
        fonts: Map<String, String>? = null
    ): KPLottieAnimation {
        return transformFonts(animation, animationRules, fonts, null, null)
    }

    fun transformFonts(
        animation: KPLottieAnimation,
        animationRules: KPAnimationRules? = null,
        fonts: Map<String, String>? = null,
        fontModel: FontModel? = null,
        texts: List<String>? = null
    )
        : KPLottieAnimation {
        val animationResult = animation.copy()
        if (fonts == null && fontModel == null) return animationResult

        if (animationRules == null) {
            val fontName = fontModel?.fontName?.substringAfterLast("/")?.substringBefore(".")
            val splitFont = fontName?.split('-')
            val ascent = fontName?.let { delegate.getAscent("gM", it, 108.0) }
            val fontFamily = splitFont?.get(0)
            val fontStyle = splitFont?.get(1)
            val font = Font(
                fName = fontName!!,
                fFamily = fontFamily!!,
                fStyle = fontStyle!!,
                ascent = JsonPrimitive(value = ascent)
            )
            val textAlign = fontModel.textAlign
            // Adding the font to the list of Font
            if (animationResult.fonts?.list?.none { it.fName == font.fName } == true) {
                font.let {
                    animationResult.fonts?.list =
                        animationResult.fonts?.list?.plus(font) ?: mutableListOf(font)
                }
            }
            val textLayer =
                animationResult.layers.find { it.nm == "text1" && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
            val textTemplateLayer =
                animationResult.layers.find { it.nm == "text1_template" && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
            if (textLayer != null && textTemplateLayer != null) {
                textLayer.t.d.k.firstOrNull()?.s?.f = font.fName
                textTemplateLayer.t.d.k.firstOrNull()?.s?.f = font.fName
                textAlign?.let {

                    val condition =
                        KPTextJustify.from(textAlign)?.name != textLayer.t.d.k.firstOrNull()?.s?.j?.name
                    if (condition) {
                        when (KPTextJustify.from(textAlign)) {
                            KPTextJustify.LEFT -> {
                                textLayer.ef?.find {it.nm.toString().removeSurrounding("\"") == "Repos_X"}?.ef?.get(0)?.v?.k =
                                    KPMultiDimensionalPrimitive(
                                        JsonPrimitive(
                                            delegate.getTextLayerWidth(
                                                texts?.get(0) as String,
                                                font.fName,
                                                textLayer.t.d.k.firstOrNull()?.s?.s.toString()
                                                    .toDouble()
                                            ) / -2
                                        )
                                    )
                            }

                            KPTextJustify.RIGHT -> {
                                textLayer.ef?.find {it.nm.toString().removeSurrounding("\"") == "Repos_X"}?.ef?.get(0)?.v?.k =
                                    KPMultiDimensionalPrimitive(
                                        JsonPrimitive(
                                            delegate.getTextLayerWidth(
                                                texts?.get(0) as String,
                                                font.fName,
                                                textLayer.t.d.k.firstOrNull()?.s?.s.toString()
                                                    .toDouble()
                                            ) / 2
                                        )
                                    )
                            }

                            KPTextJustify.CENTER -> {
                                textLayer.ef?.find {it.nm.toString().removeSurrounding("\"") == "Repos_X"}?.ef?.get(0)?.v?.k =
                                    KPMultiDimensionalPrimitive(
                                        JsonPrimitive(
                                            0
                                        )
                                    )
                            }

                            else -> {}
                        }
                    }
                    textLayer.t.d.k.firstOrNull()?.s?.j = KPTextJustify.from(textAlign)
                    textLayer.ef?.find { it.nm.toString() == "\"T_Alignment\"" }?.ef?.firstOrNull()?.v?.k =
                        KPMultiDimensionalPrimitive(
                            JsonPrimitive(textAlign)
                        )
                    textTemplateLayer.t.d.k.firstOrNull()?.s?.j = KPTextJustify.from(textAlign)
                }
            }
            return animationResult
        }

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = parseFontKey(fonts, layerRule.fontKey)
                // Adding the font to the list of Font
                if (font != null) {
                    if (animationResult.fonts?.list?.none { it.fName == font.fName } == true) {
                        font.let {
                            animationResult.fonts?.list =
                                animationResult.fonts?.list?.plus(font) ?: mutableListOf(font)
                        }
                    }
                }
                val textLayer =
                    animationResult.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                if (font != null && textLayer != null) {
                    textLayer.t.d.k.firstOrNull()?.s?.f = font.fName
                }
            }
        }
        return animationResult
    }

    private fun parseFontKey(fonts: Map<String, String>?, fontKey: String): Font? {
        if (fonts != null) {
            val fontPath = fonts[fontKey] ?: return null
            val fontName = fontPath.substringAfterLast("/").substringBefore(".")
            val splitFont = fontName.split('-')
            val ascent = delegate.getAscent("gM", fontName, 108.0)
            if (splitFont.size == 1) return Font(
                fName = fontName,
                fFamily = splitFont[0],
                fStyle = splitFont[0],
                ascent = JsonPrimitive(value = ascent)
            )
            if (splitFont.size != 2) return null
            val fontFamily = splitFont[0]
            val fontStyle = splitFont[1]
            return Font(
                fName = fontName,
                fFamily = fontFamily,
                fStyle = fontStyle,
                ascent = JsonPrimitive(value = ascent)
            )
        }
        return null
    }
}