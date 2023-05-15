package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules

class KPAnimationTransformer(
    private val functionsDelegate: KPAnimationTransformerFunctionsDelegate
    ) {
    fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>? = null,
        fonts: Map<String, String>? = null
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
        val variableTransformer = KPVariableTransformer(delegate = functionsDelegate)
        val animationVariableTransformed = variableTransformer.transformVariables(
            animation = animationTextTransformed,
            animationRules = animationRules
        )

        println("animationTextTransformed")
        return json.encodeToString(animationVariableTransformed)
    }
}