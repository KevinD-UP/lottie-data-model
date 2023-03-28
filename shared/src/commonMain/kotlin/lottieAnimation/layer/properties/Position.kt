package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

@Serializable
sealed class Position

@Serializable
data class MultiDimensional(
    val k: JsonArray? = null,
    val x: JsonElement? = null,
    val a: JsonPrimitive? = null,
    val ix: JsonPrimitive? = null,
    val l: JsonPrimitive? = null,
    val s: JsonElement? = null,
    val y: JsonElement? = null,
) : Position()

@Serializable
data class MultiDimensionalKeyframed(
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