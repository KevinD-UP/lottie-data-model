package transformer

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lottieAnimation.KPLottieAnimation
import lottieAnimation.rules.properties.KPAnimationRules
import kotlin.js.JsName

@kotlinx.serialization.ExperimentalSerializationApi
abstract class KPAnimationTransformer(
    private val functionsDelegate: KPAnimationTransformerFunctionsDelegate
    ) {

    @JsName("transform")
    abstract fun transform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>? = null,
        fonts: Map<String, String>? = null,
        fontsModels: Map<String, FontModel>? = null,
        colors: Map<String, String>? = null,
        scale: Scale? = null,
        size: AnimationSize? = null,
        effects: Effects? = null
    ): String?

    fun commonTransform(
        lottieJsonString: String,
        animationRulesJsonString: String,
        texts: List<String>? = null,
        fonts: Map<String, String>? = null,
        fontsModels: Map<String, FontModel>? = null,
        colors: Map<String, String>? = null,
        scale: Scale? = null,
        size: AnimationSize? = null,
        effects: Effects? = null
    ) : String? {
      try {
        val json = Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val lottieAnimation = json.decodeFromString<KPLottieAnimation?>(lottieJsonString) ?: return null
        val animationRules = json.decodeFromString<KPAnimationRules?>(animationRulesJsonString) ?: return null

        println("enter font transformer")
        val fontTransformer = KPFontTransformer(functionsDelegate)
        val animationFontTransformed = fontTransformer.transformFonts(
            animation = lottieAnimation,
            animationRules = animationRules,
            fonts = fonts,
            fontsModels = fontsModels,
            texts = texts
        )
        println("enter text transformer")
        val textTransformer = KPTextTransformer()
        val animationTextTransformed = textTransformer.transformTexts(
            animation = animationFontTransformed,
            animationRules = animationRules,
            texts = texts
        )
        println("enter color transformer")
        val colorTransformer = KPColorTransformer()
        val animationColorTransformed = colorTransformer.transformColor(
            animation = animationTextTransformed,
            animationRules = animationRules,
            colors = colors
        )

        println("enter opacity transformer")
        val opacityTransformer = KPOpacityTransformer()
        val animationOpacityTransformed = opacityTransformer.transformOpacity(
          animation = animationColorTransformed,
          animationRules = animationRules,
          colors = colors
        )

        println("enter size transformer")
        val sizeTransformer = KPSizeTransformer()
        val animationSizeTransformed = sizeTransformer.transformSize(
          animation = animationOpacityTransformed,
          size = size
        )

        println("enter scale transformer")
        val scaleTransformer = KPScaleTransformer()
        val animationScaleTransformed = scaleTransformer.transformScale(
          animation = animationSizeTransformed,
          scale = scale
        )

        println("enter effects transformer")
        val effectTransformer = KPEffectTransformer()
        val animationEffectTransformed = effectTransformer.transformEffect(animation = animationScaleTransformed, effects = effects)

        println("enter variable transformer")
        val variableTransformer = KPVariableTransformer(delegate = functionsDelegate)
        val animationVariableTransformed = variableTransformer.transformVariables(
            animation = animationEffectTransformed,
            animationRules = animationRules
        )

        val result = json.encodeToString(animationVariableTransformed)
        println("animationTextTransformed = $result")
        return result
      } catch (e: SerializationException) {
        // Handle serialization error
        println("An error occurred during deserialization: ${e.message}")
        return ""
      } catch (e: Exception) {
        // Handle other types of exceptions
        println("An unexpected error occurred: ${e.message}")
        return ""
      }
    }
}