package lottieAnimation.transformer

import lottieAnimation.LottieAnimation
import lottieAnimation.layer.LayerType
import lottieAnimation.layer.TextLayer
import lottieAnimation.rules.properties.AnimationRules

class FontTransformer {

    fun transformFonts(animation: LottieAnimation, animationRules: AnimationRules)
        : LottieAnimation
    {
        var copyAnimation: LottieAnimation = animationRules.copy()

        animationRules.layerRules.forEach { layerRule ->
            if (layerRule.fontKey != null) {
                val font = animation.fonts?.list?.find { it.fName == layerRule.fontKey }
                val layer = animation.layers.find { it.ind == layerRule.ind }
                if (font != null && layer != null) {
                    when(layer) {
                        is TextLayer -> {  }
                        else -> {}
                    }
                }
            }
        }
        return animation
    }
}