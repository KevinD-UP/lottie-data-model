package io.kannelle.models

data class VSPIP constructor(
    var source: String,
    var duration: Float,
    var startAt: Float?,
    var resizeMode: String?,
    var effect: VSPIPEffect?,
    var audioVolume: Float?,
    var trim: VSTimeRange?,
    var transform: MediaTransform? = null,
    internal var orientation: Int,
    internal var height: Int,
    internal var width: Int,
    internal var mediaType: String
) {

    companion object {
        const val COVER = "cover"
        const val CONTAIN = "contain"

        const val MEDIA_VIDEO = "video"
        const val MEDIA_IMAGE = "image"
    }

    val effectiveDuration: Float
        get() = if (trim?.duration != null) {
            trim?.duration ?: 0f
        } else {
            if (trim?.startAt != null) {
                duration - (trim?.startAt ?: 0f)
            } else {
                duration
            }
        }
}