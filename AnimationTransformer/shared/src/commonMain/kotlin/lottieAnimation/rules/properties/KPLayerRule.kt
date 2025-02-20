package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class KPLayerRule(
    val ind: Int,
    val layerName: String?,
    val textInd: List<Int>?,
    val lines: Int = 1,
    val maxLines: Int = 1,
    val minWidth: Int?,
    val maxWidth: Int?,
    val maxHeight: Int?,
    val fontKey: String?,
    val minFontSize: Int?,
    val colorKey: String?,
    val colorKeys: List<String>?,
    val fillColorKey: String?,
    val opacityKey: String?,
    val gradientColorKey: List<String>?,
    val shadowKey: String?,
    val shadowOpacityKey: String?,
    val shadowColorKey: String?,
    val separator: String?
)