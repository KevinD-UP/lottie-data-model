package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
sealed class Shape

@Serializable
data class ShapeEllipse(
    val mn: String,
    val nm: String,
    val d: JsonPrimitive,
    val ty: String,
    val p: JsonObject,
    val s: JsonObject,
) : Shape()

@Serializable
data class ShapeFill(
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonObject,
    val c: JsonObject,
    val bm: Int,
) : Shape()

@Serializable
data class ShapeGFill(
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonObject,
    val s: JsonObject,
    val e: JsonObject,
    val t: JsonObject,
    val h: JsonObject,
    val a: JsonObject,
    val g: JsonObject,
) : Shape()

@Serializable
data class ShapeGStroke(
    val mn: String,
    val nm: String,
    val ty: String,
    val o: JsonObject,
    val s: JsonObject,
    val e: JsonObject,
    val t: JsonObject,
    val h: JsonObject,
    val a: JsonObject,
    val g: JsonObject,
    val w: JsonObject,
    val lc: JsonPrimitive,
    val lj: JsonPrimitive,
    val ml: JsonPrimitive
) : Shape()

@Serializable
data class ShapeGroup(
    val mn: String,
    val nm: String,
    val ty: String,
    val np: JsonPrimitive,
    val it: JsonArray,
    val cix: Int,
    val bm: Int,
    val ix: Int,
    val hd: Boolean
) : Shape()

@Serializable
data class ShapeMerge(
    val mn: String,
    val nm: String,
    val ty: String,
    val mm: JsonPrimitive,
) : Shape()

@Serializable
data class ShapeRect(
    val mn: String,
    val nm: String,
    val d: JsonPrimitive,
    val ty: String,
    val p: JsonObject,
    val s: JsonObject,
    val r: JsonObject
) : Shape()

@Serializable
data class ShapeRepeater(
    val mn: String,
    val nm: String,
    val ty: String,
    val c: JsonObject,
    val o: JsonObject,
    val m: JsonPrimitive,
    val tr: JsonObject
) : Shape()

@Serializable
data class ShapeRound(
    val mn: String,
    val nm: String,
    val ty: String,
    val r: JsonObject
) : Shape()

@Serializable
data class ShapeShape(
    val mn: String,
    val nm: String,
    val d: JsonPrimitive,
    val ty: String,
    val ks: JsonObject
) : Shape()

@Serializable
data class ShapeStar(
    val mn: String,
    val nm: String,
    val d: JsonPrimitive,
    val ty: String,
    val p: JsonObject,
    val ir: JsonObject,
    val `is`: JsonObject,
    val or: JsonObject,
    val os: JsonObject,
    val r: JsonObject,
    val pt: JsonObject,
    val sy: JsonObject
) : Shape()

@Serializable
data class ShapeStroke(
    val mn: String,
    val nm: String,
    val ty: String,
    val lc: JsonPrimitive,
    val lj: JsonPrimitive,
    val ml: JsonPrimitive,
    val o: JsonObject,
    val w: JsonObject,
    val c: JsonObject
) : Shape()

@Serializable
data class ShapeTransform(
    val nm: String,
    val a: JsonObject,
    val p: JsonObject,
    val s: JsonObject,
    val r: JsonObject,
    val o: JsonObject,
    val sk: JsonObject,
    val sa: JsonObject,
    val cix: Int,
) : Shape()

@Serializable
data class ShapeTrim(
    val mn: String,
    val nm: String,
    val ty: String,
    val s: JsonObject,
    val e: JsonObject,
    val o: JsonObject,
    val m: Int,
    val ix: Int,
    val hd: Boolean
) : Shape()