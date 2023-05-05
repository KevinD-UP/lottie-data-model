package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.KPMultiDimensionalListOrPrimitiveSerializer

@Serializable
sealed class KPMultiDimensional

@Serializable
data class KPMultiDimensionalSimple(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    val k: KPMultiDimensionalListOrPrimitive? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : KPMultiDimensional()

@Serializable
data class KPMultiDimensionalKeyframed(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    val k: KPMultiDimensionalListOrPrimitive? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val ti: JsonArray? = null,
    val to: JsonArray? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : KPMultiDimensional()
