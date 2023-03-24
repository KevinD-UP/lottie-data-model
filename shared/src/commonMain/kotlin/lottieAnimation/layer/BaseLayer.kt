package lottieAnimation.layer

import kotlinx.serialization.json.JsonElement

interface BaseLayer: VisualObject {
    /**
     * NOTE : added manually by @xaba after looking at animations on lottieFiles
     * index of something...
     */
    val ct: Long?

    /**
     * Whether the layer is threedimensional
     * default: 0
     */
    val ddd: BooleanInt

    /**
     * Whether the layer is hidden
     */
    val hd: Boolean?

    /**
     * Layer type
     */
    val ty: LayerType

    /**
     * Index that can be used for parenting and referenced in expressions
     */
    val ind: Int?

    /**
     * Parent index, Must be the ind property of another layer
     */
    val parent: Int?

    /**
     * Time Stretch
     */
    val sr: Double?

    /**
     * Frame when the layer becomes visible
     */
    val ip: JsonElement

    /**
     * Frame when the layer becomes invisible
     */
    val op: JsonElement

    /**
     * Start Time
     */
    val st: JsonElement
}