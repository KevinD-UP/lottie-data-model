package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LayerRuleSerializer : KSerializer<LayerRule> {
    override fun serialize(encoder: Encoder, value: LayerRule) {
        encoder.encodeInt(value.ind)
        encoder.encodeNullableSerializableValue(ListSerializer(Int.serializer()), value.textInd)
        encoder.encodeNullableSerializableValue(Int.serializer(), value.lines)
        encoder.encodeInt(value.maxLines)
        encoder.encodeNullableSerializableValue(Int.serializer(), value.minWidth)
        encoder.encodeNullableSerializableValue(Int.serializer(), value.maxWidth)
        encoder.encodeNullableSerializableValue(Int.serializer(), value.maxHeight)
        encoder.encodeNullableSerializableValue(String.serializer(), value.fontKey)
        encoder.encodeNullableSerializableValue(Int.serializer(), value.minFontSize)
        encoder.encodeNullableSerializableValue(String.serializer(), value.colorKey)
        encoder.encodeNullableSerializableValue(ListSerializer(String.serializer()), value.colorKeys)
        encoder.encodeNullableSerializableValue(String.serializer(), value.fillColorKey)
        encoder.encodeNullableSerializableValue(String.serializer(), value.opacityKey)
        encoder.encodeNullableSerializableValue(ListSerializer(String.serializer()), value.gradientColorKey)
        encoder.encodeNullableSerializableValue(String.serializer(), value.shadowKey)
        encoder.encodeNullableSerializableValue(String.serializer(), value.shadowOpacityKey)
        encoder.encodeNullableSerializableValue(String.serializer(), value.separator)
    }

    override fun deserialize(decoder: Decoder): LayerRule {
        val ind = decoder.decodeInt()
        val textInd = decoder.decodeNullableSerializableValue(ListSerializer(Int.serializer()))
        val lines = decoder.decodeNullableSerializableValue(Int.serializer())
        val maxLines = decoder.decodeInt()
        val minWidth = decoder.decodeNullableSerializableValue(Int.serializer())
        val maxWidth = decoder.decodeNullableSerializableValue(Int.serializer())
        val maxHeight = decoder.decodeNullableSerializableValue(Int.serializer())
        val fontKey = decoder.decodeNullableSerializableValue(String.serializer())
        val minFontSize = decoder.decodeNullableSerializableValue(Int.serializer())
        val colorKey = decoder.decodeNullableSerializableValue(String.serializer())
        val colorKeys = decoder.decodeNullableSerializableValue(ListSerializer(String.serializer()))
        val fillColorKey = decoder.decodeNullableSerializableValue(String.serializer())
        val opacityKey = decoder.decodeNullableSerializableValue(String.serializer())
        val gradientColorKey = decoder.decodeNullableSerializableValue(ListSerializer(String.serializer()))
        val shadowKey = decoder.decodeNullableSerializableValue(String.serializer())
        val shadowOpacityKey = decoder.decodeNullableSerializableValue(String.serializer())
        val separator = decoder.decodeNullableSerializableValue(String.serializer())

        return LayerRule(
            ind,
            textInd,
            lines,
            maxLines,
            minWidth,
            maxWidth,
            maxHeight,
            fontKey,
            minFontSize,
            colorKey,
            colorKeys,
            fillColorKey,
            opacityKey,
            gradientColorKey,
            shadowKey,
            shadowOpacityKey,
            separator
        )
    }
}