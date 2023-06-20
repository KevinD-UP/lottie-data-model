package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.serializers.KPShapeListSerializer

@Serializable
sealed class KPLayer {
    abstract var ind: Int?
    abstract var ty: KPLayerType
    abstract var nm: String?
}

@Serializable
data class KPPrecompositionLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null,

    /**
     * Reference Id
     * ID of the precomp as specified in the assets
     */
    var refId: String,

    /**
     * Width of the clipping rect
     */
    var w: Int,

    /**
     * Height of the clipping rect
     */
    var h: Int,

    /**
     * Time Remapping
     */
    var tm: JsonElement? = null
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.PRECOMP_LAYER
}

@Serializable
data class KPSolidLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null,

    /**
     * Color of the layer, unlike most other places, the color is a #rrggbb hex string
     */
    var sc: String,

    /**
     * Height
     */
    var sh: JsonElement,

    /**
     * Width
     */
    var sw: JsonElement
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.SOLID_LAYER
}

@Serializable
data class KPImageLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null,

    /**
     * Image ID
     * ID of the image as specified in the assets
     */
    var refId: String = "",
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.IMAGE_LAYER
}

@Serializable
data class KPShapeLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null,

    /**
     * Shapes
     */
    @Serializable(with = KPShapeListSerializer::class)
    var shapes: List<KPShape>?
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.SHAPE_LAYER
}

@Serializable
data class KPAudioLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,

    /**
     * Audio Settings
     */
    var au: JsonElement,

    /**
     * Sound Id
     * ID of the sound as specified in the assets
     */
    var refId: String
) : KPLayer(), KPBaseLayer {
    override var ty: KPLayerType = KPLayerType.AUDIO_LAYER
}

@Serializable
data class KPCameraLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,

    /**
     * Layer Transform
     */
    var ks: JsonElement,

    /**
     * Perspective
     */
    var pe: JsonElement
) : KPLayer(), KPBaseLayer {
    override var ty: KPLayerType = KPLayerType.CAMERA_LAYER
}

@Serializable
data class KPDataLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,

    /**
     * Data source Id
     * ID of the data source in assets
     */
    var refId: String
) : KPLayer(), KPBaseLayer {
    override var ty: KPLayerType = KPLayerType.DATA_LAYER
}

@Serializable
data class KPNullLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.NULL_LAYER
}

@Serializable
data class KPTextLayer(
    override var ct: Long? = null,
    override var nm: String? = null,
    override var mn: String? = null,
    override var ddd: KPBooleanInt,
    override var hd: Boolean? = null,
    override var ind: Int? = null,
    override var parent: Int? = null,
    override var sr: JsonElement? = null,
    override var ip: JsonElement,
    override var op: JsonElement,
    override var st: JsonElement,
    override var cp: Boolean? = null,
    override var ks: KPTransform,
    override var ao: Int? = null,
    override var tt: JsonElement? = null,
    override var td: Int? = null,
    override var hasMask: Boolean? = null,
    override var masksProperties: JsonElement? = null,
    override var ef: List<KPTextEffect>? = null,
    override var mb: Boolean? = null,
    override var sy: JsonObject? = null,
    override var bm: JsonElement? = null,
    override var cl: String? = null,
    override var ln: String? = null,
    override var tg: String? = null,

    /**
     * Data
     */
    var t: KPTextData
) : KPLayer(), KPVisualLayer {
    override var ty: KPLayerType = KPLayerType.TEXT_LAYER
}