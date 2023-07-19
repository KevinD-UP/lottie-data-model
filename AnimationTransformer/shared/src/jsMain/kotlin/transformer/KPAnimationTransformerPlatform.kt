package transformer

class KPAnimationTransformerJs(functionsDelegate: KPAnimationTransformerFunctionsDelegate): KPAnimationTransformer(functionsDelegate) {
    private fun entriesOf(jsObject: dynamic): List<Pair<String, Any?>> =
        (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
            .invoke(jsObject)
            .map { entry -> entry[0] as String to entry[1] }

    private fun mapOf(jsObject: dynamic): Map<String, Any?> =
        entriesOf(jsObject).toMap()

    @JsName("transformJs")
    fun transformJs(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>? = null,
        fontsJson: dynamic = null,
        colorsJson: dynamic = null
    ) : String? {
        var fonts: Map<String, String>? = null
        var colors: Map<String, String>?  = null

        if(fontsJson != null) {
            fonts = mapOf(fontsJson).mapValues { it.value?.toString() ?: "" }
        }

        if(colorsJson != null) {
            colors = mapOf(colorsJson).mapValues { it.value?.toString() ?: "" }
        }

        return transform(
            lottieJsonString = lottieJsonString,
            animationRulesJsonString = animationRulesJsonString,
            texts = texts?.toList(),
            fonts = fonts,
            colors = colors
        )
    }

    override fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>?,
        fonts: Map<String, String>?,
        colors: Map<String, String>?
    ): String? {
        return commonTransform(
            lottieJsonString,
            animationRulesJsonString,
            texts,
            fonts,
            colors
        )
    }
}
