package io.kannelle.models

data class VSComputeFile constructor(
    var source: String?,
    var audioVolume: Float?,
    var musicVolume: Float?,
    val excludeWatermark: Boolean?,
    var trim: VSTimeRange?,
    var pip: VSPIP?,
    val bgAudio: VSBgAudio?,
    val animation: VSAnimation?,
    val index: Int?,
    val projectId: String?,
    val excludeMask: Boolean?,
    val visualIndex: Int?,
    var transform: MediaTransform? = null,
    @JvmField internal var startAt: Float,
    @JvmField internal var endAt: Float,
    @JvmField internal var duration: Float,
    internal var hasVideo: Boolean,
    internal var hasAudio: Boolean,
    internal var width: Int,
    internal var height: Int,
    internal var orientation: Int
) {

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