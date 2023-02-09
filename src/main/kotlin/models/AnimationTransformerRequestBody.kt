package io.kannelle.models

data class AnimationTransformerRequestBody constructor(
    val animation: VSAnimation,
    val duration: Float,
    val viewWidth: Int,
    val viewHeight: Int,
    val subtitleHeight: Int,
    val watermark: VSWatermark? = null,
    val charterId: String
)