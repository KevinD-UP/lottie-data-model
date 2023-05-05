package lottieAnimation.layer

import kotlinx.serialization.Serializable
import lottieAnimation.layer.serializers.KPBooleanIntSerializer

@Serializable(with = KPBooleanIntSerializer::class)
enum class KPBooleanInt {
    FALSE, TRUE;

    companion object {
        infix fun from(value: Int): KPBooleanInt? = KPBooleanInt.values().firstOrNull { v -> v.ordinal == value }
    }
}