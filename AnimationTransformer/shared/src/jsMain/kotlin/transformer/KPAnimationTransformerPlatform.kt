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
                    nestedMap["size"]?.toInt()
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
        colorsJson: dynamic = null
    ) : String? {
        var fonts: Map<String, String>? = null
        var colors: Map<String, String>?  = null
        var fontsModels: Map<String, FontModel>? = null

        if(fontsJson != null) {
            fonts = mapOf(fontsJson).mapValues { it.value?.toString() ?: "" }
        }

        if(fontsModelsJson != null) {
            fontsModels = convertToObject(fontsModelsJson)
        }

        if(colorsJson != null) {
            colors = mapOf(colorsJson).mapValues { it.value?.toString() ?: "" }
        }

        return transform(
            lottieJsonString = lottieJsonString,
            animationRulesJsonString = animationRulesJsonString,
            texts = texts?.toList(),
            fonts = fonts,
            fontsModels = fontsModels,
            colors = colors
        )
    }

    override fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>?,
        fonts: Map<String, String>?,
        fontsModels: Map<String, FontModel>?,
        colors: Map<String, String>?
    ): String? {
        return commonTransform(
            lottieJsonString,
            animationRulesJsonString,
            texts,
            fonts,
            fontsModels,
            colors
        )
    }
}
