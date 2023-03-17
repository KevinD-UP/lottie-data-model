package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.LayerTypeSerializer

@Serializable(with = LayerTypeSerializer::class)
enum class LayerType {
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
        infix fun from(value: Int): LayerType? = LayerType.values().firstOrNull { v -> v.ordinal == value }
    }
}