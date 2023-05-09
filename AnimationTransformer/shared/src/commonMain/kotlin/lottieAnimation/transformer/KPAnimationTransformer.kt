package lottieAnimation.transformer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules

class KPAnimationTransformer {
    fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        fonts: Map<String, String>? = null
    )
            : String?
    {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
        }
        val lottieAnimation = json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val animationRules = json.decodeFromString<KPAnimationRules?>(animationRulesJsonString) ?: return null
        val fontTransformer = KPFontTransformer()
        val animationFontTransformed = fontTransformer.transformFonts(
            animation = lottieAnimation,
            animationRules = animationRules,
            fonts = fonts
        )
        println("animationFontTransformed")
        return json.encodeToString(animationFontTransformed)
    }
}