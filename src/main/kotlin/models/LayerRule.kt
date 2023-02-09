package io.kannelle.models

import kotlinx.serialization.Serializable

@Serializable
data class LayerRule(
    val ind: Int,
    val textInd: List<Int>? = null,
    val lines: Int? = 1,
    val maxLines: Int = 1,
    val minWidth: Int? = null,
    val maxWidth: Int? = null,
    val maxHeight: Int? = null,
    val fontKey: String? = null,
    val minFontSize: Int? = null,
    val colorKey: String? = null,
    val colorKeys: List<String>? = null,
    val fillColorKey: String? = null,
    val opacityKey: String? = null,
    val gradientColorKey: List<String>? = null,
    val shadowKey: String? = null,
    val shadowOpacityKey: String? = null,
    val separator: String? = null
)