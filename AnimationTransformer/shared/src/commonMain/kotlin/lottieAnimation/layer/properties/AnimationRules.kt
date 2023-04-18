package lottieAnimation.layer.properties

import kotlinx.serialization.Serializable

@Serializable
data class AnimationRules(
    var layerRules: List<LayerRule>,
    var assetRules: AssetRules?,
    var variables: List<AnimationRuleVariable>?)