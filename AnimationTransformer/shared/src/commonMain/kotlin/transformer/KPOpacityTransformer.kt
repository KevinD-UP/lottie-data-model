package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPShapeGroup
import lottieAnimation.layer.KPShapeLayer
import lottieAnimation.layer.KPShapeTransform
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
					targetLayer.shapes?.forEach { shape ->
						when(shape) {
							is KPShapeGroup -> {
								when (val shapeTransform = shape.it[2]){
									is KPShapeTransform -> shapeTransform.o?.k = KPMultiDimensionalPrimitive(JsonPrimitive(opacity))
									else -> {}
								}
							}
							else -> {}
						}
					}
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