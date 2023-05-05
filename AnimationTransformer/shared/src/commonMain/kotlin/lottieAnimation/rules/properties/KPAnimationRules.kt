package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class KPAnimationRules(
    var layerRules: List<KPLayerRule>,
    var assetRules: KPAssetRules?,
    var variables: List<KPAnimationRuleVariable>?)