package io.kannelle.models

import java.util.*

data class VSAnimation constructor(
    var name: String,
    var overlap: Boolean?,
    var position: VSAnimationPosition,
    var delay: Float?,
    var duration: Float?,
    var texts: Array<String>?,
    var theme: String,
    var colors: Map<String, String>?,
    var fonts: Map<String, String>?
) {

    var id: Int = 0

    val isLowerThird: Boolean
        get() = position.code != VSAnimationPosition.FULLSCREEN

    val isMask: Boolean
        get() = name == MaskAnimationName.MINI


    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VSAnimation) return false
        return name == other.name &&
                overlap == other.overlap &&
                position == other.position &&
                delay == other.delay &&
                duration == other.duration &&
                Arrays.equals(texts, other.texts) &&
                theme == other.theme &&
                colors == other.colors &&
                fonts == other.fonts
    }

}