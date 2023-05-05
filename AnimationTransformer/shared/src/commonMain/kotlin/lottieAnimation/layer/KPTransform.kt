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
    val a: KPMultiDimensional? = null,
    /**
     * Position
     * Transform Position
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val p: KPMultiDimensional? = null,
    /**
     * Scale
     * Transform Scale
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val s: KPMultiDimensional? = null,
    /**
     * Rotation
     * Transform Rotation
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val r: KPMultiDimensional? = null,
    /**
     * Opacity
     * Transform Opacity
     */
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val o: KPMultiDimensional? = null,
    /**
     * Position X
     * Transform Position X
     */
    val px: JsonObject? = null,
    /**
     * Position Y
     * Transform Position Y
     */
    val py: JsonObject? = null,
    /**
     * Position Z
     * Transform Position Z
     */
    val pz: JsonObject? = null,
    /**
     * Skew
     * Transform Skew
     */
    val sk: JsonObject? = null,
    /**
     * Skew axis
     * Transform Skew axis
     */
    val sa: JsonObject? = null,
)
