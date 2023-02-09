package io.kannelle.models

data class AssetRules(
    val originalColors: Map<String, String>?,
    val shadowColorKey: String?,
    val shadowOpacityKey: String?
)