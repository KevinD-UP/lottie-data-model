package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPNullLayer
import lottieAnimation.layer.properties.KPMultiDimensionalList
import lottieAnimation.layer.properties.KPMultiDimensionalNodePrimitive

data class Scale(
	val width: Long,
	val height: Long,
	val depth: Long?
)

class KPScaleTransformer {

	fun transformScale(
		animation: KPLottieAnimation,
		scale: Scale?
	): KPLottieAnimation {
		val animationResult = animation.copy()
		if(scale == null) return animationResult

		val targetLayer = animationResult.layers.find { it.nm == "control_panel" && it.ty == KPLayerType.NULL_LAYER } as? KPNullLayer
		if(targetLayer != null) {
			val depth = scale.depth ?: 100
			targetLayer.ks.s?.k =
				KPMultiDimensionalList(
					listOf(
						KPMultiDimensionalNodePrimitive(JsonPrimitive(scale.width)),
						KPMultiDimensionalNodePrimitive(JsonPrimitive(scale.height)),
						KPMultiDimensionalNodePrimitive(JsonPrimitive(depth))
					)
				)
		}
		return animationResult
	}

}