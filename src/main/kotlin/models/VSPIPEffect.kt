package io.kannelle.models

data class VSPIPEffect constructor(
    val name: String,
    val value: Float
) {

    companion object {
        const val NONE = "NONE"
        const val ZOOMIN = "ZOOMIN"
        const val ZOOMOUT = "ZOOMOUT"
        const val TRANSLATION_RIGHT = "TRANSLATION_RIGHT"
        const val TRANSLATION_LEFT = "TRANSLATION_LEFT"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VSPIPEffect) return false
        return name == other.name &&
                value == other.value
    }
}