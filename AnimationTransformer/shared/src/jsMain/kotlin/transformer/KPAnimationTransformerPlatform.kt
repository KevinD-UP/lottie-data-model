package transformer

@JsExport
class KPAnimationTransformerJs(functionsDelegate: KPAnimationTransformerFunctionsDelegate): KPAnimationTransformer(functionsDelegate) {
    private fun entriesOf(jsObject: dynamic): List<Pair<String, Any?>> =
        (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
            .invoke(jsObject)
            .map { entry -> entry[0] as String to entry[1] }

    private fun mapOf(jsObject: dynamic): Map<String, Any?> =
        entriesOf(jsObject).toMap()

    private fun convertToObject(jsObject: dynamic): Map<String, FontModel> {
        val map = mutableMapOf<String, FontModel>()

        val entries = entriesOf(jsObject)
        for (entry in entries) {
            val key = entry.first
            val value = entry.second

            if (value != null) {
                val nestedMap = mapOf(value).mapValues { it.value?.toString() ?: "" }
                val fontModel = FontModel(
                    nestedMap["name"].toString(),
                    nestedMap["size"]?.toInt(),
                    nestedMap["textAlign"]?.toInt()
                )
                map[key] = fontModel
            }
        }

        return map
    }

    @JsName("transformJs")
    fun transformJs(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: Array<String>? = null,
        fontsJson: dynamic = null,
        fontsModelsJson: dynamic = null,
        colorsJson: dynamic = null,
        scaleJson: dynamic = null,
        sizeJson: dynamic = null
    ) : String? {
        var fonts: Map<String, String>? = null
        var colors: Map<String, String>? = null
        var fontsModels: Map<String, FontModel>? = null
        var scale: Scale? = null
        var size: AnimationSize? = null

        if (fontsJson != null) {
            fonts = mapOf(fontsJson).mapValues { it.value?.toString() ?: "" }
        }

        if (fontsModelsJson != null) {
            fontsModels = convertToObject(fontsModelsJson)
        }

        if (colorsJson != null) {
            colors = mapOf(colorsJson).mapValues { it.value?.toString() ?: "" }
        }

        if (scaleJson != null) {
            val scaleMap = mapOf(scaleJson).mapValues { it.value?.toString() ?: "" }
            scale = scaleMap["width"]?.let { width ->
                scaleMap["height"]?.let { height ->
                    val depth = scaleMap["depth"]?.toLongOrNull()
                    Scale(width.toLong(), height.toLong(), depth)
                }
            }
        }

        if (sizeJson != null) {
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


        return transform(
            lottieJsonString = lottieJsonString,
            animationRulesJsonString = animationRulesJsonString,
            texts = texts?.toList(),
            fonts = fonts,
            fontsModels = fontsModels,
            colors = colors,
            scale = scale,
            size = size
        )
    }

    override fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>?,
        fonts: Map<String, String>?,
        fontsModels: Map<String, FontModel>?,
        colors: Map<String, String>?,
        scale: Scale?,
        size: AnimationSize?
    ): String? {
        return commonTransform(
            lottieJsonString,
            animationRulesJsonString,
            texts,
            fonts,
            fontsModels,
            colors,
            scale,
            size
        )
    }
}
