package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.PositionKListSerializer
import lottieAnimation.layer.serializers.PositionKSerializer

@Serializable
sealed class Position

@Serializable
data class MultiDimensional(
    @Serializable(with = PositionKListSerializer::class)
    val k: List<PositionK>? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : Position()

@Serializable
data class MultiDimensionalKeyframed(
    //@Serializable(with = PositionKSerializer::class)
    val k: JsonArray? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val ti: JsonArray? = null,
    val to: JsonArray? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : Position()
