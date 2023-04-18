package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AnimationRulesSerializer : KSerializer<AnimationRules> {
    override fun serialize(encoder: Encoder, value: AnimationRules) {
        encoder.encodeSerializableValue(ListSerializer(LayerRule.serializer()), value.layerRules)
        encoder.encodeNullableSerializableValue(AssetRules.serializer(), value.assetRules)
        encoder.encodeNullableSerializableValue(ListSerializer(AnimationRuleVariable.serializer()), value.variables)
    }

    override fun deserialize(decoder: Decoder): AnimationRules {
        val layerRules = decoder.decodeSerializableValue(ListSerializer(LayerRule.serializer()))
        val assetRules = decoder.decodeNullableSerializableValue(AssetRules.serializer())
        val variables = decoder.decodeNullableSerializableValue(ListSerializer(AnimationRuleVariable.serializer()))
        return AnimationRules(layerRules, assetRules, variables)
    }
}