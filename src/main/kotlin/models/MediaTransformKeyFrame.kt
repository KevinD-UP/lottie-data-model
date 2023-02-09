package io.kannelle.models

/**
 *
 * @param scale is the scale factor of media.
 * @param translationX is the percent x location of the point around which the media is transformed.
 * @param translationY is the percent y location of the point around which the media is transformed.
 * @param timeInterpolator is a value between 0 and 1.0 indicating our current point
 * in the animation where 0 represents the start and 1.0 represents
 * the end
 *
 */
data class MediaTransformKeyFrame(
    val scale: Float,
    val translationX: Float,
    val translationY: Float,
    val timeInterpolator: Float
)