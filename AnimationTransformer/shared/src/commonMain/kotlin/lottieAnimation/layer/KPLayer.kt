package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.serializers.KPShapeListSerializer

@Serializable
sealed class KPLayer {
    abstract val ind: Int?
    abstract val ty: KPLayerType
}

@Serializable
data class KPPrecompositionLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Reference Id
     * ID of the precomp as specified in the assets
     */
    val refId: String,

    /**
     * Width of the clipping rect
     */
    val w: Int,

    /**
     * Height of the clipping rect
     */
    val h: Int,

    /**
     * Time Remapping
     */
    val tm: JsonElement? = null
) : KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.PRECOMP_LAYER
}

@Serializable
data class KPSolidColorLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Color of the layer, unlike most other places, the color is a #rrggbb hex string
     */
    val sc: String,

    /**
     * Height
     */
    val sh: JsonElement,

    /**
     * Width
     */
    val sw: JsonElement
) : KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.SOLID_COLOR_LAYER
}

@Serializable
data class KPImageLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Image ID
     * ID of the image as specified in the assets
     */
    val refId: String = "",
): KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.IMAGE_LAYER
}

@Serializable
data class KPShapeLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Shapes
     */
    @Serializable(with = KPShapeListSerializer::class)
    val shapes: List<KPShape>?
): KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.SHAPE_LAYER
}

@Serializable
data class KPAudioLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,

    /**
     * Audio Settings
     */
    val au: JsonElement,

    /**
     * Sound Id
     * ID of the sound as specified in the assets
     */
    val refId: String
) : KPLayer(), KPBaseLayer {
    override val ty: KPLayerType = KPLayerType.AUDIO_LAYER
}

@Serializable
data class KPCameraLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,

    /**
     * Layer Transform
     */
    val ks: JsonElement,

    /**
     * Perspective
     */
    val pe: JsonElement
) : KPLayer(), KPBaseLayer {
    override val ty: KPLayerType = KPLayerType.CAMERA_LAYER
}

@Serializable
data class KPDataLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,

    /**
     * Data source Id
     * ID of the data source in assets
     */
    val refId: String
) : KPLayer(), KPBaseLayer {
    override val ty: KPLayerType = KPLayerType.DATA_LAYER
}

@Serializable
data class KPNullLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null
) : KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.NULL_LAYER
}

@Serializable
data class KPTextLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: KPBooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: KPTransform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: List<KPTextEffect>? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Data
     */
    val t: KPTextData
): KPLayer(), KPVisualLayer {
    override val ty: KPLayerType = KPLayerType.TEXT_LAYER
}