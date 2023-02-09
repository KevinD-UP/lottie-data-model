package io.kannelle.models

import kotlinx.serialization.Serializable

@Serializable
data class AnimationRules(
    var layerRules: List<LayerRule>,
    var assetRules: AssetRules? = null,
    var variables: List<AnimationRuleVariable>?)