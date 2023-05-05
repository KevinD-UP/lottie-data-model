package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class KPAssetRules(
    val originalColors: Map<String, String>?,
    val shadowColorKey: String?,
    val shadowOpacityKey: String?
)