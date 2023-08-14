package transformer

import lottieAnimation.KPLottieAnimation

data class AnimationSize (
	val width: Long,
	val height: Long
)

class KPSizeTransformer {

	fun transformSize(animation: KPLottieAnimation, size: AnimationSize?): KPLottieAnimation {
		val animationResult = animation.copy()
		if(size == null) return animationResult

		animationResult.w = size.width
		animationResult.h = size.height

		return animationResult
	}

}