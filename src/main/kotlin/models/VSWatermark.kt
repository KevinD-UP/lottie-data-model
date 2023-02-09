package io.kannelle.models

data class VSWatermark constructor(
    val source: String,
    val size: VSSize?,
    val position: VSPosition,
    val opacity: Float? = 0.9f
)