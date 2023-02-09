package io.kannelle.models

data class VSBgAudio constructor(
    var source: String,
    val volume: Float?,
    val trim: VSTimeRange?,
    internal var hasAudio: Boolean
)