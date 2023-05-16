package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.KPShapeTypeSerializer

@Serializable(with = KPShapeTypeSerializer::class)
enum class KPShapeType(val value: String) {
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
        infix fun from(value: String): KPShapeType? =
            KPShapeType.values().find { it.value == value }
    }
}