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
    val name: String,
    val size: Int?,
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
        animationRules: KPAnimationRules,
        fonts: Map<String, String>? = null,
        fontsModels: Map<String, FontModel>? = null,
        texts: List<String>? = null
    )
        : KPLottieAnimation
    {
        val animationResult = animation.copy()
        if(fonts == null && fontsModels == null) return animationResult

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = parseFontKey(fonts, fontsModels, layerRule.fontKey)
                val size = parseFontSize(fontsModels, layerRule.fontKey)
                val textAlign = parseTextAlign(fontsModels, layerRule.fontKey)
                // Adding the font to the list of Font
                font?.let {
                    animationResult.fonts?.list =
                        animationResult.fonts?.list?.plus(font) ?: mutableListOf(font)
                }
                val textLayer =
                    animationResult.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.TEXT_LAYER } as? KPTextLayer
                if (font != null && textLayer != null) {
                    textLayer.t.d.k.firstOrNull()?.s?.f = font.fName
                    size?.let {
                        textLayer.t.d.k.firstOrNull()?.s?.s = JsonPrimitive(size)
                    }
                    textAlign?.let {
                        textLayer.t.d.k.firstOrNull()?.s?.j = KPTextJustify.from(textAlign)
                        textLayer.ef?.find { it.nm.toString() == "\"T_Alignment\"" }?.ef?.firstOrNull()?.v?.k =
                            KPMultiDimensionalPrimitive(
                                JsonPrimitive(textAlign)
                            )
                        val panelX = (animationResult.layers.find { it.nm == "control_panel" } as KPNullLayer)
                            .ef?.find { it.nm.toString() == "\"Panel_X\"" }
                            ?.ef?.find { it.nm.toString() == "\"Slider\"" }?.v
                        when (KPTextJustify.from(textAlign)) {
                            KPTextJustify.LEFT -> {
                                panelX?.k =
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
                                panelX?.k =
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
                                panelX?.k =
                                    KPMultiDimensionalPrimitive(
                                        JsonPrimitive(
                                            0
                                        )
                                    )
                            }

                            else -> {}
                        }

                    }
                }
            }
        }
        return animationResult
    }

    private fun parseFontSize(fontsModels: Map<String, FontModel>?, fontKey: String): Int? {
        val fonts = fontsModels ?: return null
        return fonts[fontKey]?.size
    }

    private fun parseTextAlign(fontsModels: Map<String, FontModel>?, fontKey: String): Int? {
        val fonts = fontsModels ?: return null
        return fonts[fontKey]?.textAlign
    }


    private fun parseFontKey(fonts: Map<String, String>?, fontsModels: Map<String, FontModel>?, fontKey: String): Font? {

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
        } else if (fontsModels != null) {
            val fontModel = fontsModels[fontKey] ?: return null
            val fontName = fontModel.name.substringAfterLast("/").substringBefore(".")
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