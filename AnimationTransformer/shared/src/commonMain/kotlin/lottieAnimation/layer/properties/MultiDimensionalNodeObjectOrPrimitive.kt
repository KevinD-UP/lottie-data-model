package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
sealed class MultiDimensionalNodeObjectOrPrimitive

@Serializable
data class MultiDimensionNodeObject(
    val t: JsonPrimitive? = null,
    val s: JsonArray? = null,
    val i: JsonObject? = null,
    val o: JsonObject? = null,
    val h: JsonPrimitive? = null,
    val e: JsonArray? = null,
    val to: JsonArray? = null,
    val ti: JsonArray? = null
): MultiDimensionalNodeObjectOrPrimitive()


@Serializable
data class MultiDimensionalNodePrimitive(
    val value: JsonPrimitive
): MultiDimensionalNodeObjectOrPrimitive()