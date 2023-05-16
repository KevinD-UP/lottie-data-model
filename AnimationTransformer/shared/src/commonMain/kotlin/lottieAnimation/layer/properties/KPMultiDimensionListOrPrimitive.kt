package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.KPMultiDimensionalNodeListSerializer

@Serializable
sealed class KPMultiDimensionalListOrPrimitive

@Serializable
data class KPMultiDimensionalList(
    @Serializable(with = KPMultiDimensionalNodeListSerializer::class)
    var values: List<KPMultiDimensionalNodeObjectOrPrimitive>
) : KPMultiDimensionalListOrPrimitive()

@Serializable
data class KPMultiDimensionalPrimitive(
    var value: JsonPrimitive
) : KPMultiDimensionalListOrPrimitive()