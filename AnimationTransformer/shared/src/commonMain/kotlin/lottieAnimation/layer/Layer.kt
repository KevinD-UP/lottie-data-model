package lottieAnimation.layer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import lottieAnimation.layer.serializers.ShapeListSerializer

@Serializable
sealed class Layer {
    abstract val ind: Int?
}

@Serializable
data class PrecompositionLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
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
) : Layer(), VisualLayer {
    override val ty: LayerType = LayerType.PRECOMP_LAYER
}

@Serializable
data class SolidColorLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
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
) : Layer(), VisualLayer {
    override val ty: LayerType = LayerType.SOLID_COLOR_LAYER
}

@Serializable
data class ImageLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
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
): Layer(), VisualLayer {
    override val ty: LayerType = LayerType.IMAGE_LAYER
}

@Serializable
data class ShapeLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Shapes
     */
    @Serializable(with = ShapeListSerializer::class)
    val shapes: List<Shape>?
): Layer(), VisualLayer {
    override val ty: LayerType = LayerType.SHAPE_LAYER
}

@Serializable
data class AudioLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
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
) : Layer(), BaseLayer {
    override val ty: LayerType = LayerType.AUDIO_LAYER
}

@Serializable
data class CameraLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
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
) : Layer(), BaseLayer {
    override val ty: LayerType = LayerType.CAMERA_LAYER
}

@Serializable
data class DataLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
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
) : Layer(), BaseLayer {
    override val ty: LayerType = LayerType.DATA_LAYER
}

@Serializable
data class NullLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null
) : Layer(), VisualLayer {
    override val ty: LayerType = LayerType.NULL_LAYER
}

@Serializable
data class TextLayer(
    override val ct: Long? = null,
    override val nm: String? = null,
    override val mn: String? = null,
    override val ddd: BooleanInt,
    override val hd: Boolean? = null,
    override val ind: Int? = null,
    override val parent: Int? = null,
    override val sr: JsonElement? = null,
    override val ip: JsonElement,
    override val op: JsonElement,
    override val st: JsonElement,
    override val cp: Boolean? = null,
    override val ks: Transform,
    override val ao: Int? = null,
    override val tt: JsonElement? = null,
    override val td: Int? = null,
    override val hasMask: Boolean? = null,
    override val masksProperties: JsonElement? = null,
    override val ef: JsonArray? = null,
    override val mb: Boolean? = null,
    override val sy: JsonObject? = null,
    override val bm: JsonElement? = null,
    override val cl: String? = null,
    override val ln: String? = null,
    override val tg: String? = null,

    /**
     * Data
     */
    val t: TextData
): Layer(), VisualLayer {
    override val ty: LayerType = LayerType.TEXT_LAYER
}