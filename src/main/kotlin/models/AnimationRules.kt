package io.kannelle.models

data class AnimationRules(
    var layerRules: List<LayerRule>,
    var assetRules: AssetRules?,
    var variables: List<AnimationRuleVariable>?)