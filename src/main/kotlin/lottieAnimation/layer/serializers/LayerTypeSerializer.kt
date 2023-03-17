package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.LayerType

object LayerTypeSerializer : KSerializer<LayerType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("layerType", PrimitiveKind.INT)

    override fun serialize(output: Encoder, obj: LayerType) {
        output.encodeInt(obj.ordinal)
    }

    override fun deserialize(input: Decoder): LayerType {
        val layerType = LayerType.from(input.decodeInt())
        if (layerType === null) {
            throw Exception("Error in layerType deserialization")
        }
        return layerType
    }
}