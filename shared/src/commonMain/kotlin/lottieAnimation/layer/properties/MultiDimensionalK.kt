package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
sealed class MultiDimensionalK

@Serializable
data class MultiDimensionObject(
    val t: JsonPrimitive? = null,
    val s: JsonArray? = null,
    val i: JsonObject? = null,
    val o: JsonObject? = null,
    val h: JsonPrimitive? = null,
    val e: JsonArray? = null,
    val to: JsonArray? = null,
    val ti: JsonArray? = null
): MultiDimensionalK()



@Serializable
data class MultiDimensionalKPrimitive(
    val value: JsonPrimitive
): MultiDimensionalK()