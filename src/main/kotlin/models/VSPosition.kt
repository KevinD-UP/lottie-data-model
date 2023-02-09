package io.kannelle.models

data class VSPosition constructor(
    val code: String,
    val x: Float?
) {

    companion object {
        const val TOPLEFT = "TOPLEFT"
        const val TOPRIGHT = "TOPRIGHT"
        const val BOTTOMLEFT = "BOTTOMLEFT"
        const val BOTTOMRIGHT = "BOTTOMRIGHT"
    }

}