package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.MultiDimensionalNodeListSerializer

@Serializable
sealed class MultiDimensionalListOrPrimitive

@Serializable
data class MultiDimensionalList(
    @Serializable(with = MultiDimensionalNodeListSerializer::class)
    val values: List<MultiDimensionalNodeObjectOrPrimitive>
): MultiDimensionalListOrPrimitive()

@Serializable
data class MultiDimensionalPrimitive(
    val value: JsonPrimitive
): MultiDimensionalListOrPrimitive()