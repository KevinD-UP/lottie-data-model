package lottieAnimation.layer

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

interface KPVisualLayer: KPBaseLayer {
    /**
     * Collapse Transform
     */
    var cp: Boolean?

    /**
     * Layer transform
     */
    var ks: KPTransform

    /**
     * Auto Orient
     */
    var ao: Int?

    /**
     * Matte Mode
     */
    var tt: JsonElement?

    /**
     * Matte Target
     */
    var td: Int?

    /**
     * Whether the layer has masks applied
     */
    var hasMask: Boolean?

    /**
     * Mask properties
     */
    var masksProperties: JsonElement?

    /**
     * List of layer effects
     */
    var ef: List<KPTextEffect>?

    /**
     * Whether motion blur is enabled for the layer
     */
    var mb: Boolean?

    /**
     * Styling effects for this layer
     */
    var sy: JsonObject?

    /**
     * Blend Mode
     */
    var bm: JsonElement?

    /**
     * CSS class used by the SVG renderer
     */
    var cl: String?

    /**
     * Layer XML ID
     */
    var ln: String?

    /**
     * Layer XML tag name
     */
    var tg: String?
}