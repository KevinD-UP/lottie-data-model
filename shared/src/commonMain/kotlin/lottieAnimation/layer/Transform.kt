package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.properties.Position
import lottieAnimation.layer.serializers.PositionSerializer
import lottieAnimation.layer.serializers.ShapeListSerializer

@Serializable
data class Transform(
    /**
     * Anchor point
     * Transform Anchor Point
     */
    val a: JsonObject? = null,
    /**
     * Position
     * Transform Position
     */
    @Serializable(with = PositionSerializer::class)
    val p: Position? = null,
    /**
     * Scale
     * Transform Scale
     */
    val s: JsonObject? = null,
    /**
     * Rotation
     * Transform Rotation
     */
    val r: JsonObject? = null,
    /**
     * Opacity
     * Transform Opacity
     */
    val o: JsonObject? = null,
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
