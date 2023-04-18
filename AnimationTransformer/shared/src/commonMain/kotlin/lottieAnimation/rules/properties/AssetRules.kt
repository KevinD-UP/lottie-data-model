package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class AssetRules(
    val originalColors: Map<String, String>?,
    val shadowColorKey: String?,
    val shadowOpacityKey: String?
)