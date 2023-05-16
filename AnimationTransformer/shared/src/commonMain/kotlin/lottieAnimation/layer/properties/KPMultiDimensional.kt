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
    var k: KPMultiDimensionalListOrPrimitive? = null,
    var x: JsonElement? = null,
    var a: JsonPrimitive? = null,
    var ix: JsonPrimitive? = null,
    var l: JsonPrimitive? = null,
    var s: JsonElement? = null,
    var y: JsonElement? = null,
) : KPMultiDimensional()

@Serializable
data class KPMultiDimensionalKeyframed(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    var k: KPMultiDimensionalListOrPrimitive? = null,
    var x: JsonElement? = null,
    var a: JsonPrimitive? = null,
    var ix: JsonPrimitive? = null,
    var ti: JsonArray? = null,
    var to: JsonArray? = null,
    var l: JsonPrimitive? = null,
    var s: JsonElement? = null,
    var y: JsonElement? = null,
) : KPMultiDimensional()
