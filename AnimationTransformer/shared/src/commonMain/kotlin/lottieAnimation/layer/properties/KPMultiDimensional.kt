package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.KPMultiDimensionalListOrPrimitiveSerializer

@Serializable
sealed class KPMultiDimensional {
    abstract var k: KPMultiDimensionalListOrPrimitive?
    abstract var x: JsonElement?
    abstract var a: JsonPrimitive?
    abstract var ix: JsonPrimitive?
    abstract var l: JsonPrimitive?
    abstract var s: JsonElement?
    abstract var y: JsonElement?
}

@Serializable
data class KPMultiDimensionalSimple(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    override var k: KPMultiDimensionalListOrPrimitive? = null,
    override var x: JsonElement? = null,
    override var a: JsonPrimitive? = null,
    override var ix: JsonPrimitive? = null,
    override var l: JsonPrimitive? = null,
    override var s: JsonElement? = null,
    override var y: JsonElement? = null,
) : KPMultiDimensional()

@Serializable
data class KPMultiDimensionalKeyframed(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    override var k: KPMultiDimensionalListOrPrimitive? = null,
    override var x: JsonElement? = null,
    override var a: JsonPrimitive? = null,
    override var ix: JsonPrimitive? = null,
    override var l: JsonPrimitive? = null,
    override var s: JsonElement? = null,
    override var y: JsonElement? = null,
    var ti: JsonArray? = null,
    var to: JsonArray? = null,
) : KPMultiDimensional()
