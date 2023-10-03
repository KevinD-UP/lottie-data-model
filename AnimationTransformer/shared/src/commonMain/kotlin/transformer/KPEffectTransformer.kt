package transformer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPLayerType
import lottieAnimation.layer.KPNullLayer
import lottieAnimation.layer.properties.KPMultiDimensionalPrimitive

@Serializable
data class Effects(
	val panelX: Double?,
	val panelY: Double?,
	val bannerMarginWidth: Double?,
	val bannerMarginHeight: Double?,
	val bannerSkew: Double?,
	val bannerRoundness: Double?,
	val bannerStrokeWidth: Double?,
	val bannerDropShadow: Double?,
	val textCharaspacing: Double?,
	val textStrokeWidth: Double?,
	val textDropShadow: Double?
)

enum class EffectName(val propertyName: String) {
	PANEL_X("Panel_X"),
	PANEL_Y("Panel_Y"),
	BANNER_MARGIN_WIDTH("B_Marge_W"),
	BANNER_MARGIN_HEIGHT("B_Marge_H"),
	BANNER_SKEW("B_Skew"),
	BANNER_ROUNDNESS("B_Roundness"),
	BANNER_STROKE_WIDTH("B_Stroke_Width"),
	BANNER_DROP_SHADOW("B_Shadow"),
	TEXT_CHARASPACING("T_Charaspacing"),
	TEXT_STROKE_WIDTH("T_Stroke_Width"),
	TEXT_DROP_SHADOW("T_Shadow")
}

class KPEffectTransformer {

	fun transformEffect(animation: KPLottieAnimation, effects: Effects?): KPLottieAnimation {
		val animationResult = animation.copy()

		if (effects == null) {
			return animationResult
		}

		(animationResult.layers.find { it.nm == "control_panel" && it.ty == KPLayerType.NULL_LAYER } as KPNullLayer).ef?.forEach { effect ->

			val sliderEffect = effect.ef.find { it.nm.toString().removeSurrounding("\"") == "Slider" }
			val opacityEffect = effect.ef.find { it.nm.toString().removeSurrounding("\"") == "Opacity" }

			when (EffectName.values()
				.find { it.propertyName == effect.nm.toString().removeSurrounding("\"") } as EffectName) {
				EffectName.PANEL_X -> {
					if (effects.panelX != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.panelX))
				}
				EffectName.PANEL_Y -> {
					if (effects.panelY != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.panelY))
				}

				EffectName.BANNER_MARGIN_WIDTH -> {
					if (effects.bannerMarginWidth != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerMarginWidth * 2))
				}

				EffectName.BANNER_MARGIN_HEIGHT -> {
					if (effects.bannerMarginHeight != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerMarginHeight * 2))
				}

				EffectName.BANNER_SKEW -> {
					if (effects.bannerSkew != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerSkew))
				}

				EffectName.BANNER_ROUNDNESS -> {
					if (effects.bannerRoundness != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerRoundness))
				}

				EffectName.BANNER_STROKE_WIDTH -> {
					if (effects.bannerStrokeWidth != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerStrokeWidth))
				}

				EffectName.TEXT_CHARASPACING -> {
					if (effects.textCharaspacing != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.textCharaspacing))
				}

				EffectName.TEXT_STROKE_WIDTH -> {
					if (effects.textStrokeWidth != null)
						sliderEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.textStrokeWidth))
				}

				EffectName.BANNER_DROP_SHADOW -> {
					if (effects.bannerDropShadow != null) {
						opacityEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.bannerDropShadow*2))
					}
				}

				EffectName.TEXT_DROP_SHADOW -> {
					if (effects.textDropShadow != null) {
						opacityEffect?.v?.k =
							KPMultiDimensionalPrimitive(JsonPrimitive(effects.textDropShadow*2))
					}
				}
				else -> {}
			}
		}
		return animationResult
	}
}