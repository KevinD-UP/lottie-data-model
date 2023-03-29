package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.BooleanIntSerializer

@Serializable(with = BooleanIntSerializer::class)
enum class BooleanInt {
    FALSE, TRUE;

    companion object {
        infix fun from(value: Int): BooleanInt? = BooleanInt.values().firstOrNull { v -> v.ordinal == value }
    }
}