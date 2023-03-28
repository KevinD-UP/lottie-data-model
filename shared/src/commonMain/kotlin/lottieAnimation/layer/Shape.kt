package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.properties.MultiDimensional
import lottieAnimation.layer.serializers.MultiDimensionalSerializer
import lottieAnimation.layer.serializers.ShapeListSerializer

@Serializable
sealed class Shape

@Serializable
data class ShapeEllipse(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    @Serializable(with = MultiDimensionalSerializer::class)
    val p: MultiDimensional? = null,
    @Serializable(with = MultiDimensionalSerializer::class)
    val s: MultiDimensional? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeFill(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonObject,
    val c: JsonObject,
    val bm: Int? = null,
    val r: Int? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeGFill(
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
) : Shape()

@Serializable
data class ShapeGStroke(
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
) : Shape()

@Serializable
data class ShapeGroup(
    val mn: String,
    val nm: String,
    val ty: String,
    val np: JsonPrimitive? = null,
    @Serializable(with = ShapeListSerializer::class)
    val it: List<Shape>,
    val cix: Int? = null,
    val bm: Int,
    val ix: Int,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeMerge(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val mm: JsonPrimitive? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeRect(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    @Serializable(with = MultiDimensionalSerializer::class)
    val p: MultiDimensional? = null,
    @Serializable(with = MultiDimensionalSerializer::class)
    val s: MultiDimensional? = null,
    val r: JsonElement? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeRound(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val ty: String,
    val r: JsonElement? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeShape(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    val ks: JsonElement? = null,
    val ix: Int,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeStar(
    val ind: JsonPrimitive? = null,
    val mn: String,
    val nm: String,
    val d: JsonElement? = null,
    val ty: String,
    val p: JsonObject,
    val ir: JsonObject,
    val `is`: JsonObject,
    val or: JsonObject,
    val os: JsonObject,
    val r: JsonObject,
    val pt: JsonObject,
    val sy: JsonObject,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeStroke(
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
) : Shape()

@Serializable
data class ShapeTransform(
    val ind: JsonPrimitive? = null,
    val nm: String,
    val ty: String,
    val a: JsonObject? = null,
    @Serializable(with = MultiDimensionalSerializer::class)
    val p: MultiDimensional? = null,
    @Serializable(with = MultiDimensionalSerializer::class)
    val s: MultiDimensional? = null,
    val r: JsonObject? = null,
    val o: JsonElement? = null,
    val sk: JsonObject? = null,
    val sa: JsonObject? = null,
    val cix: Int? = null,
    val hd: Boolean? = null,
) : Shape()

@Serializable
data class ShapeTrim(
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
) : Shape()