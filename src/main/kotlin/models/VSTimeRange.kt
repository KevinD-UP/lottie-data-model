package io.kannelle.models

data class VSTimeRange constructor(
    val startAt: Float?,
    val endAt: Float?
) {

    val duration: Float?
        get() = if (endAt != null) {
            if (startAt != null) {
                endAt - startAt
            } else {
                endAt
            }
        } else null

}