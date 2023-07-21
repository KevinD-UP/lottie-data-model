package lottieAnimation.layer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.properties.KPMultiDimensional
import lottieAnimation.layer.properties.KPMultiDimensionalListOrPrimitive
import lottieAnimation.layer.properties.KPMultiDimensionalSimple
import lottieAnimation.layer.serializers.KPMultiDimensionalListOrPrimitiveSerializer
import lottieAnimation.layer.serializers.KPMultiDimensionalSerializer
import lottieAnimation.layer.serializers.KPShapeListSerializer

@Serializable
sealed class KPShape {
    abstract var ind: JsonPrimitive?
    abstract var ty: String
    abstract var bm: Int?
}

@Serializable
data class KPShapeEllipse(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    var d: JsonElement? = null,
    override var ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var s: KPMultiDimensional? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeFill(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var o: JsonObject,
    var c: KPShapeFillColor,
    override var bm: Int? = null,
    var r: Int? = null,
    var hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeFillColor(
    var a: JsonPrimitive? = null,
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    var k: KPMultiDimensionalListOrPrimitive,
    var ix: JsonPrimitive? = null
)

@Serializable
data class KPShapeGFill(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var o: JsonElement? = null,
    var s: JsonElement? = null,
    var e: JsonElement? = null,
    var t: JsonElement? = null,
    var h: JsonElement? = null,
    var a: JsonElement? = null,
    var g: KPShapeGFillColor,
    var r: JsonElement? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeGFillColor(
    var p: JsonPrimitive? = null,
    var k: KPMultiDimensionalSimple
)

@Serializable
data class KPShapeGStroke(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var o: JsonElement? = null,
    var s: JsonElement? = null,
    var e: JsonElement? = null,
    var t: JsonElement? = null,
    var h: JsonElement? = null,
    var a: JsonElement? = null,
    var g: JsonElement? = null,
    var w: JsonElement? = null,
    var lc: JsonPrimitive? = null,
    var lj: JsonPrimitive? = null,
    var ml: JsonPrimitive? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeGroup(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var np: JsonPrimitive? = null,
    @Serializable(with = KPShapeListSerializer::class)
    var it: List<KPShape>,
    var cix: Int? = null,
    override var bm: Int? = null,
    var ix: Int? = null,
    var hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeMerge(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var mm: JsonPrimitive? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeRect(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    var d: JsonElement? = null,
    override var ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var s: KPMultiDimensional? = null,
    var r: JsonElement? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeRound(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var r: JsonElement? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeShape(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    var d: JsonElement? = null,
    override var ty: String,
    var ks: JsonElement? = null,
    var ix: Int? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeStar(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    var d: JsonElement? = null,
    override var ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var p: KPMultiDimensional? = null,
    var ir: JsonObject,
    var `is`: JsonObject,
    var or: JsonObject,
    var os: JsonObject,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var r: KPMultiDimensional? = null,
    var pt: JsonObject,
    var sy: JsonObject,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeStroke(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var lc: JsonPrimitive? = null,
    var lj: JsonPrimitive? = null,
    var ml: JsonPrimitive? = null,
    var o: JsonElement? = null,
    var w: JsonObject? = null,
    var c: KPShapeStrokeColor? = null,
    override var bm: Int? = null,
    var d: JsonElement? = null,
    var hd: Boolean? = null,
) : KPShape()

@Serializable
data class KPShapeStrokeColor(
    var a: JsonPrimitive? = null,
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    var k: KPMultiDimensionalListOrPrimitive,
    var ix: JsonPrimitive? = null
)

@Serializable
data class KPShapeTransform(
    override var ind: JsonPrimitive? = null,
    var nm: String? = null,
    override var ty: String,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var a: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var p: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var s: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var r: KPMultiDimensional? = null,
    @Serializable(with = KPMultiDimensionalSerializer::class)
    var o: KPMultiDimensional? = null,
    var sk: JsonObject? = null,
    var sa: JsonObject? = null,
    var cix: Int? = null,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()

@Serializable
data class KPShapeTrim(
    override var ind: JsonPrimitive? = null,
    var mn: String? = null,
    var nm: String? = null,
    override var ty: String,
    var s: JsonObject? = null,
    var e: JsonObject? = null,
    var o: JsonElement? = null,
    var m: Int,
    var ix: Int,
    var hd: Boolean? = null,
    override var bm: Int? = null,
) : KPShape()