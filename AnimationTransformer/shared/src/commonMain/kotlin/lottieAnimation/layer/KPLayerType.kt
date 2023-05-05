package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.KPLayerTypeSerializer

@Serializable(with = KPLayerTypeSerializer::class)
enum class KPLayerType {
    PRECOMP_LAYER,
    SOLID_COLOR_LAYER,
    IMAGE_LAYER,
    NULL_LAYER,
    SHAPE_LAYER,
    TEXT_LAYER,
    AUDIO_LAYER,
    VIDEO_PLACEHOLDER,
    IMAGE_SEQUENCE,
    VIDEO_LAYER,
    IMAGE_PLACEHOLDER,
    GUIDE_LAYER,
    ADJUSTMENT_LAYER,
    CAMERA_LAYER,
    LIGHT_LAYER,
    DATA_LAYER;

    companion object {
        infix fun from(value: Int): KPLayerType? = KPLayerType.values().firstOrNull { v -> v.ordinal == value }
    }
}