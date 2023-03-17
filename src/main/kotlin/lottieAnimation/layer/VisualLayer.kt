package lottieAnimation.layer

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

interface VisualLayer: BaseLayer {
    /**
     * Collapse Transform
     */
    val cp: Boolean?

    /**
     * Layer transform
     */
    val ks: JsonElement

    /**
     * Auto Orient
     */
    val ao: Int?

    /**
     * Matte Mode
     */
    val tt: JsonElement?

    /**
     * Matte Target
     */
    val td: Int?

    /**
     * Whether the layer has masks applied
     */
    val hasMask: Boolean?

    /**
     * Mask properties
     */
    val masksProperties: JsonElement?

    /**
     * List of layer effects
     */
    val ef: JsonArray?

    /**
     * Whether motion blur is enabled for the layer
     */
    val mb: Boolean?

    /**
     * Styling effects for this layer
     */
    val sy: JsonObject?

    /**
     * Blend Mode
     */
    val bm: JsonElement?

    /**
     * CSS class used by the SVG renderer
     */
    val cl: String?

    /**
     * Layer XML ID
     */
    val ln: String?

    /**
     * Layer XML tag name
     */
    val tg: String?
}