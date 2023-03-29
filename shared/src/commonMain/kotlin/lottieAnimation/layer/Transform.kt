package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.properties.MultiDimensional
import lottieAnimation.layer.serializers.MultiDimensionalSerializer

@Serializable
data class Transform(
    /**
     * Anchor point
     * Transform Anchor Point
     */
    @Serializable(with = MultiDimensionalSerializer::class)
    val a: MultiDimensional? = null,
    /**
     * Position
     * Transform Position
     */
    @Serializable(with = MultiDimensionalSerializer::class)
    val p: MultiDimensional? = null,
    /**
     * Scale
     * Transform Scale
     */
    @Serializable(with = MultiDimensionalSerializer::class)
    val s: MultiDimensional? = null,
    /**
     * Rotation
     * Transform Rotation
     */
    @Serializable(with = MultiDimensionalSerializer::class)
    val r: MultiDimensional? = null,
    /**
     * Opacity
     * Transform Opacity
     */
    @Serializable(with = MultiDimensionalSerializer::class)
    val o: MultiDimensional? = null,
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
