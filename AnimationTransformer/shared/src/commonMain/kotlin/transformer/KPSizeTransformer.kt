package transformer

import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPNullLayer
import lottieAnimation.layer.properties.KPMultiDimensionalList
import lottieAnimation.layer.properties.KPMultiDimensionalNodePrimitive

data class AnimationSize (
	val width: Long,
	val height: Long
)

class KPSizeTransformer {

	fun transformSize(animation: KPLottieAnimation, size: AnimationSize?): KPLottieAnimation {
		val animationResult = animation.copy()
		val targetLayer = animationResult.layers.find { it.nm == "control_panel" && it.ty == KPLayerType.NULL_LAYER } as? KPNullLayer
		if(size == null) return animationResult

		animationResult.w = size.width
		animationResult.h = size.height

		if (targetLayer != null) {
			targetLayer.ks.p?.k = KPMultiDimensionalList(
				listOf(
					KPMultiDimensionalNodePrimitive(JsonPrimitive(size.width/2)),
					KPMultiDimensionalNodePrimitive(JsonPrimitive(size.height/2)),
					KPMultiDimensionalNodePrimitive(JsonPrimitive(0))
				)
			)
		}

		return animationResult
	}

}