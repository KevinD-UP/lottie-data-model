package io.kannelle.models

data class VSAnimationPosition constructor(
    var code: String,
    var x: Float?,
    var y: Float?
) {
    var id: Int = 0

    companion object {
        const val FULLSCREEN = "FULLSCREEN"
        const val CENTER = "CENTER"
        const val TOPLEFT = "TOPLEFT"
        const val TOPRIGHT = "TOPRIGHT"
        const val BOTTOMLEFT = "BOTTOMLEFT"
        const val BOTTOMRIGHT = "BOTTOMRIGHT"
        const val BOTTOM = "BOTTOM"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VSAnimationPosition) return false
        return code == other.code &&
                x == other.x &&
                y == other.y
    }

}
