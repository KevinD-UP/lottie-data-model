package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.KPLayerType

object KPLayerTypeSerializer : KSerializer<KPLayerType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("layerType", PrimitiveKind.INT)

    override fun serialize(output: Encoder, obj: KPLayerType) {
        output.encodeInt(obj.ordinal)
    }

    override fun deserialize(input: Decoder): KPLayerType {
        val layerType = KPLayerType.from(input.decodeInt())
        if (layerType === null) {
            throw Exception("Error in layerType deserialization")
        }
        return layerType
    }
}