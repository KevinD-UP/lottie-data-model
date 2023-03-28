package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.PositionKListSerializer

@Serializable
sealed class MultiDimensional

@Serializable
data class MultiDimensionalSimple(
    @Serializable(with = PositionKListSerializer::class)
    val k: List<MultiDimensionalK>? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : MultiDimensional()

@Serializable
data class MultiDimensionalKeyframed(
    @Serializable(with = PositionKListSerializer::class)
    val k: List<MultiDimensionalK>? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val ti: JsonArray? = null,
    val to: JsonArray? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : MultiDimensional()
