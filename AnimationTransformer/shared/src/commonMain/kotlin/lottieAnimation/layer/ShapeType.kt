package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.LayerTypeSerializer
import lottieAnimation.layer.serializers.ShapeTypeSerializer

@Serializable(with = ShapeTypeSerializer::class)
enum class ShapeType(val value: String) {
    ELLIPSE("el"),
    FILL("fl"),
    G_FILL("gf"),
    G_STROKE("gs"),
    GROUP("gr"),
    MERGE("mm"),
    RECT("rc"),
    ROUND("rd"),
    SHAPE("sh"),
    STAR("sr"),
    STROKE("st"),
    TRANSFORM("tr"),
    TRIM("tm");

    companion object {
        infix fun from(value: String): ShapeType? = ShapeType.values().find { it.value == value }
    }
}