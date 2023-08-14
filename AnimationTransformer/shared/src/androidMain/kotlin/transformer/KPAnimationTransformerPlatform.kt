package transformer

import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
class KPAnimationTransformerAndroid(functionsDelegate: KPAnimationTransformerFunctionsDelegate): KPAnimationTransformer(functionsDelegate) {
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
