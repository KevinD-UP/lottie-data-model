package transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPNullLayer

@JsExport
class KPAnimationTransformerJs(functionsDelegate: KPAnimationTransformerFunctionsDelegate): KPAnimationTransformer(functionsDelegate) {
    private fun entriesOf(jsObject: dynamic): List<Pair<String, Any?>> =
        (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
            .invoke(jsObject)
            .map { entry -> entry[0] as String to entry[1] }

    private fun mapOf(jsObject: dynamic): Map<String, Any?> =
        entriesOf(jsObject).toMap()

    private fun convertToFontModel(jsObject: dynamic): FontModel {
        val map = mapOf(jsObject)
        val fontName = map["fontName"]?.toString()
        val textAlign = map["textAlign"]?.toString()?.toInt()

        return FontModel(fontName, textAlign)
    }

    private fun mapToEffects(jsObject: dynamic): Effects {
        val map = mapOf(jsObject)

        val panelX = map["panelX"]?.toString()?.toDouble()
        val panelY = map["panelY"]?.toString()?.toDouble()
        val bannerMarginWidth = map["bannerMarginWidth"]?.toString()?.toDouble()
        val bannerMarginHeight = map["bannerMarginHeight"]?.toString()?.toDouble()
        /*
        val bannerSkew = map["bannerSkew"].toString().toDouble() as? Double
        val bannerRoundness = map["bannerRoundness"].toString().toDouble() as? Double
        val bannerStrokeWidth = map["bannerStrokeWidth"].toString().toDouble() as? Double
        val textCharaspacing = map["textCharaspacing"].toString().toDouble() as? Double
        val textStrokeWidth = map["textStrokeWidth"].toString().toDouble() as? Double
        val textDropShadow = map["textDropShadow"].toString().toDouble() as? Double
         */

        return Effects(
            panelX,
            panelY,
            bannerMarginWidth,
            bannerMarginHeight,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }


    @JsName("transformJs")
    fun transformJs(
        lottieJsonString: String,
        texts: Array<String>? = null,
        fontsJson: dynamic = null,
        fontModelJson: dynamic = null,
        colorsJson: dynamic = null,
        scaleJson: dynamic = null,
        sizeJson: dynamic = null,
        effectsJson: dynamic = null
    ) : String? {
        var fonts: Map<String, String>? = null
        var colors: Map<String, String>? = null
        var fontModel: FontModel? = null
        var scale: Scale? = null
        var size: AnimationSize? = null
        var effects: Effects? = null

        if (fontsJson != null) {
            println("Create Font Map")
            fonts = mapOf(fontsJson).mapValues { it.value?.toString() ?: "" }
        }

        if (fontModelJson != null) {
            println("Create Font Model Map")
            fontModel = convertToFontModel(fontModelJson)
        }

        if (colorsJson != null) {
            println("Create Color Map")
            colors = mapOf(colorsJson).mapValues { it.value?.toString() ?: "" }
        }

        if (scaleJson != null) {
            println("Create Scale object")
            val scaleMap = mapOf(scaleJson).mapValues { it.value?.toString() ?: "" }
            scale = scaleMap["width"]?.let { width ->
                scaleMap["height"]?.let { height ->
                    val depth = scaleMap["depth"]?.toLongOrNull()
                    Scale(width.toLong(), height.toLong(), depth)
                }
            }
        }

        if (sizeJson != null) {
            println("Create AnimationSize object")
            val sizeMap = mapOf(sizeJson).mapValues { it.value?.toString() ?: "" }
            size = sizeMap["width"]?.let {
                sizeMap["height"]?.let { it1 ->
                    AnimationSize(
                        it.toLong(),
                        it1.toLong()
                    )
                }
            }
        }

        if (effectsJson != null) {
            println("Create Effects object")
            effects = mapToEffects(effectsJson)
        }


        return transform(
            lottieJsonString = lottieJsonString,
            animationRulesJsonString = "",
            texts = texts?.toList(),
            fonts = fonts,
            fontModel = fontModel,
            colors = colors,
            scale = scale,
            size = size,
            effects = effects
        )
    }

    fun getAnimationSize(lottieJsonString: String): String? {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val lottieAnimation =
            json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val animationSize = AnimationSize(width = lottieAnimation.w, height = lottieAnimation.h)
        // Convert the object to a JSON string
        return Json.encodeToString(animationSize)
    }

    fun getAnimationPosition(lottieJsonString: String): String? {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val lottieAnimation =
            json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val controlPanelLayer = (lottieAnimation.layers.find { it.nm == "control_panel" && it.ty == KPLayerType.NULL_LAYER } as KPNullLayer)
        val panelX = controlPanelLayer.ef?.find { it.nm.toString().removeSurrounding("\"") == "panelX"  }
        val panelY = controlPanelLayer.ef?.find { it.nm.toString().removeSurrounding("\"") == "panelY"  }
        val position = Effects(
            panelX = panelX?.ef?.get(0)?.v?.k.toString().toDouble(),
            panelY = panelY?.ef?.get(0)?.v?.k.toString().toDouble(),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        // Convert the object to a JSON string
        return Json.encodeToString(position)
    }

    fun getScale(lottieJsonString: String): String? {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val lottieAnimation =
            json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val scaleLayer = lottieAnimation.layers.find {it.nm == "scale" && it.ty == KPLayerType.NULL_LAYER } as? KPNullLayer
        val scaleArray = scaleLayer?.ks?.s?.k.toString()
        // Remove the square brackets and split the string by commas
        val elements = scaleArray
            .substring(1, scaleArray.length - 1)
            .split(",")
            .map { it.trim().toLong() }

        // Convert the list of elements to an array
        val numbersArray = elements.toLongArray()

        val scale = Scale(width = numbersArray[0], height = numbersArray[1], depth = numbersArray[2])
        // Convert the object to a JSON string
        return Json.encodeToString(scale)
    }

    override fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>?,
        fonts: Map<String, String>?,
        fontModel: FontModel?,
        colors: Map<String, String>?,
        scale: Scale?,
        size: AnimationSize?,
        effects: Effects?
    ): String? {
        return commonTransform(
            lottieJsonString,
            animationRulesJsonString,
            texts,
            fonts,
            fontModel,
            colors,
            scale,
            size,
            effects
        )
    }
}
