package lottieAnimation.layer.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
sealed class KPMultiDimensionalNodeObjectOrPrimitive

@Serializable
data class KPMultiDimensionNodeObject(
    @SerialName("kp_transform_key")
    var kpTransformKey: String? = null,
    var t: JsonPrimitive? = null,
    var s: JsonArray? = null,
    var i: JsonObject? = null,
    var o: JsonObject? = null,
    var h: JsonPrimitive? = null,
    var e: JsonArray? = null,
    var to: JsonArray? = null,
    var ti: JsonArray? = null
) : KPMultiDimensionalNodeObjectOrPrimitive()


@Serializable
data class KPMultiDimensionalNodePrimitive(
    var value: JsonPrimitive
) : KPMultiDimensionalNodeObjectOrPrimitive()