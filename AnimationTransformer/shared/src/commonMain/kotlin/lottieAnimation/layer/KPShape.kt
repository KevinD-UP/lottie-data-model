package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.properties.KPMultiDimensional
import lottieAnimation.layer.serializers.KPMultiDimensionalSerializer
import lottieAnimation.layer.serializers.KPShapeListSerializer

@Serializable
sealed class KPShape

@Serializable
data class KPShapeEllipse(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val s: KPMultiDimensional? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeFill(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonObject,
    val c: JsonObject,
    val bm: Int? = null,
    val r: Int? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeGFill(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonElement? = null,
    val s: JsonElement? = null,
    val e: JsonElement? = null,
    val t: JsonElement? = null,
    val h: JsonElement? = null,
    val a: JsonElement? = null,
    val g: JsonElement? = null,
    val r: JsonElement? = null,
    val hd: Boolean? = null,
    val bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeGStroke(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonElement? = null,
    val s: JsonElement? = null,
    val e: JsonElement? = null,
    val t: JsonElement? = null,
    val h: JsonElement? = null,
    val a: JsonElement? = null,
    val g: JsonElement? = null,
    val w: JsonElement? = null,
    val lc: JsonPrimitive? = null,
    val lj: JsonPrimitive? = null,
    val ml: JsonPrimitive? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeGroup(
    val mn: String,
    val nm: String,
    val ty: String,
    val np: JsonPrimitive? = null,
    @Serializable(with = KPShapeListSerializer::class)
    val it: List<KPShape>,
    val cix: Int? = null,
    val bm: Int,
    val ix: Int,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeMerge(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val mm: JsonPrimitive? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeRect(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val s: KPMultiDimensional? = null,
    val r: JsonElement? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeRound(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val r: JsonElement? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeShape(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    val ks: JsonElement? = null,
    val ix: Int,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeStar(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val p: KPMultiDimensional? = null,
    val ir: JsonObject,
    val `is`: JsonObject,
    val or: JsonObject,
    val os: JsonObject,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val r: KPMultiDimensional? = null,
    val pt: JsonObject,
    val sy: JsonObject,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeStroke(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val lc: JsonPrimitive? = null,
    val lj: JsonPrimitive? = null,
    val ml: JsonPrimitive? = null,
    val o: JsonElement? = null,
    val w: JsonObject? = null,
    val c: JsonObject? = null,
    val bm: Int,
    val d: JsonElement? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeTransform(
    val ind: JsonPrimitive? = null,
    val nm: String,
    val ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val a: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val s: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val r: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    val o: KPMultiDimensional? = null,
    val sk: JsonObject? = null,
    val sa: JsonObject? = null,
    val cix: Int? = null,
    val hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeTrim(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val s: JsonObject? = null,
    val e: JsonObject? = null,
    val o: JsonElement? = null,
    val m: Int,
    val ix: Int,
    val hd: Boolean? = null,
) : KPShape()