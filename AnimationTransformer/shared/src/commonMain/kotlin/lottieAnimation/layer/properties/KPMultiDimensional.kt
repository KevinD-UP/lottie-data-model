package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.serializers.KPMultiDimensionalListOrPrimitiveSerializer

@Serializable
sealed class KPMultiDimensional {
    abstract val k: KPMultiDimensionalListOrPrimitive?
    abstract val x: JsonElement?
    abstract val a: JsonPrimitive?
    abstract val ix: JsonPrimitive?
    abstract val l: JsonPrimitive?
    abstract val s: JsonElement?
    abstract val y: JsonElement?
}

@Serializable
data class KPMultiDimensionalSimple(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    override val k: KPMultiDimensionalListOrPrimitive? = null,
    override val x: JsonElement? = null,
    override val a: JsonPrimitive? = null,
    override val ix: JsonPrimitive? = null,
    override val l: JsonPrimitive? = null,
    override val s: JsonElement? = null,
    override val y: JsonElement? = null,
) : KPMultiDimensional()

@Serializable
data class KPMultiDimensionalKeyframed(
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    override val k: KPMultiDimensionalListOrPrimitive? = null,
    override val x: JsonElement? = null,
    override val a: JsonPrimitive? = null,
    override val ix: JsonPrimitive? = null,
    override val l: JsonPrimitive? = null,
    override val s: JsonElement? = null,
    override val y: JsonElement? = null,
    val ti: JsonArray? = null,
    val to: JsonArray? = null,
) : KPMultiDimensional()
