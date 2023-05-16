package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.properties.KPMultiDimensional
import lottieAnimation.layer.serializers.KPMultiDimensionalSerializer

@Serializable
data class KPTransform(
    /**
     * Anchor point
     * Transform Anchor Point
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var a: KPMultiDimensional? = null,
    /**
     * Position
     * Transform Position
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var p: KPMultiDimensional? = null,
    /**
     * Scale
     * Transform Scale
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var s: KPMultiDimensional? = null,
    /**
     * Rotation
     * Transform Rotation
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var r: KPMultiDimensional? = null,
    /**
     * Opacity
     * Transform Opacity
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var o: KPMultiDimensional? = null,
    /**
     * Position X
     * Transform Position X
     */
    var px: JsonObject? = null,
    /**
     * Position Y
     * Transform Position Y
     */
    var py: JsonObject? = null,
    /**
     * Position Z
     * Transform Position Z
     */
    var pz: JsonObject? = null,
    /**
     * Skew
     * Transform Skew
     */
    var sk: JsonObject? = null,
    /**
     * Skew axis
     * Transform Skew axis
     */
    var sa: JsonObject? = null,
)
