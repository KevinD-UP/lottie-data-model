package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPShapeLayer
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.layer.properties.KPMultiDimensionalPrimitive
import lottieAnimation.rules.properties.KPAnimationRules

class KPOpacityTransformer {

	fun transformOpacity(
		animation: KPLottieAnimation,
		animationRules: KPAnimationRules,
		colors: Map<String, String>?
	): KPLottieAnimation {
		val animationResult = animation.copy()
		if(colors == null) return animationResult

		animationRules.layerRules.forEach { layerRule ->
			if(layerRule.colorKey != null) {
				val color = colors[layerRule.colorKey]
				val targetLayer = animationResult.layers.find { it.ind == layerRule.ind && it.ty == KPLayerType.SHAPE_LAYER } as? KPShapeLayer
				if (color != null && targetLayer != null) {
					val opacity = color.opacityFromHex()
					targetLayer.ks.o?.k = KPMultiDimensionalPrimitive(JsonPrimitive(opacity))
				}
			}
		}

		return animationResult
	}

	private fun String.opacityFromHex(): Int {
		if (!this.startsWith('#')) {
			return 100
		}

		return when (this.length) {
			9 -> { // 8-digit hex + '#'
				// Extract the alpha component (last 2 characters)
				val alphaHex = this.takeLast(2)

				return alphaHex.toInt()
			}
			else -> 100
		}
	}
}