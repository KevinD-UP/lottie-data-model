package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AssetRulesSerializer : KSerializer<AssetRules> {
    override fun serialize(encoder: Encoder, value: AssetRules) {
        encoder.encodeNullableSerializableValue(MapSerializer(String.serializer(), String.serializer()), value.originalColors)
        encoder.encodeNullableSerializableValue(String.serializer(), value.shadowColorKey)
        encoder.encodeNullableSerializableValue(String.serializer(), value.shadowOpacityKey)
    }

    override fun deserialize(decoder: Decoder): AssetRules {
        val originalColors = decoder.decodeNullableSerializableValue(MapSerializer(String.serializer(), String.serializer()))
        val shadowColorKey = decoder.decodeNullableSerializableValue(String.serializer())
        val shadowOpacityKey = decoder.decodeNullableSerializableValue(String.serializer())
        return AssetRules(originalColors, shadowColorKey, shadowOpacityKey)
    }
}