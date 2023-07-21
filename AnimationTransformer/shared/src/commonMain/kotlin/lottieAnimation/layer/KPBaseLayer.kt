package lottieAnimation.layer

import kotlinx.serialization.json.JsonElement

interface KPBaseLayer : KPVisualObject {
    /**
     * NOTE : added manually by @xaba after looking at animations on lottieFiles
     * index of something...
     */
    var ct: Long?

    /**
     * Whether the layer is threedimensional
     * default: 0
     */
    var ddd: KPBooleanInt?

    /**
     * Whether the layer is hidden
     */
    var hd: Boolean?

    /**
     * Layer type
     */
    var ty: KPLayerType

    /**
     * Index that can be used for parenting and referenced in expressions
     */
    var ind: Int?

    /**
     * Parent index, Must be the ind property of another layer
     */
    var parent: Int?

    /**
     * Time Stretch
     */
    var sr: JsonElement?

    /**
     * Frame when the layer becomes visible
     */
    var ip: JsonElement

    /**
     * Frame when the layer becomes invisible
     */
    var op: JsonElement

    /**
     * Start Time
     */
    var st: JsonElement

    var hix: JsonElement?
}