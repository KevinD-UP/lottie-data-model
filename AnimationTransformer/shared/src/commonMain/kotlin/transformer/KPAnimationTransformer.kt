package transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName


@JsExport
@kotlinx.serialization.ExperimentalSerializationApi
class KPAnimationTransformer(
    private val functionsDelegate: KPAnimationTransformerFunctionsDelegate
    ) {

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
      texts: Array<String>? = null,
      fontsJson: dynamic = null,
      colorsJson: dynamic = null
    ) : String? {
      val fonts = mapOf(fontsJson).mapValues { it.value?.toString() ?: "" }
      val colors = mapOf(colorsJson).mapValues { it.value?.toString() ?: "" }

      return transform(
        lottieJsonString = lottieJsonString,
        animationRulesJsonString = animationRulesJsonString,
        texts = texts,
        fonts = fonts,
        colors = colors
      )
    }

    @JsName("transform")
    fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: Array<String>? = null,
        fonts: Map<String, String>? = null,
        colors: Map<String, String>? = null
    )
      : String?
    {

        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val lottieAnimation = json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val animationRules = json.decodeFromString<KPAnimationRules?>(animationRulesJsonString) ?: return null
        val fontTransformer = KPFontTransformer()
        val animationFontTransformed = fontTransformer.transformFonts(
            animation = lottieAnimation,
            animationRules = animationRules,
            fonts = fonts
        )
        val textTransformer = KPTextTransformer()
        val animationTextTransformed = textTransformer.transformTexts(
            animation = animationFontTransformed,
            animationRules = animationRules,
            texts = texts
        )

        val colorTransformer = KPColorTransformer()
        val animationColorTransformed = colorTransformer.transformColor(
            animation = animationTextTransformed,
            animationRules = animationRules,
            colors = colors
        )

        val variableTransformer = KPVariableTransformer(delegate = functionsDelegate)
        val animationVariableTransformed = variableTransformer.transformVariables(
            animation = animationColorTransformed,
            animationRules = animationRules
        )

        val result = json.encodeToString(animationVariableTransformed)
        println("animationTextTransformed = $result")
        return result
    }
}