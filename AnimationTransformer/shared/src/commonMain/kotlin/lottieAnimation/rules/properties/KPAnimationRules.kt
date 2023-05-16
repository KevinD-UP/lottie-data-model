package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class KPAnimationRules(
    val layerRules: List<KPLayerRule>,
    val assetRules: KPAssetRules?,
    val variables: List<KPAnimationRuleVariable>?
)