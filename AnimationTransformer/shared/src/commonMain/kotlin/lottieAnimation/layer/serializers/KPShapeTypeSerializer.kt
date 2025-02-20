package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.KPShapeType

object KPShapeTypeSerializer : KSerializer<KPShapeType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("shapeType", PrimitiveKind.INT)

    override fun serialize(output: Encoder, obj: KPShapeType) {
        output.encodeString(obj.toString())
    }

    override fun deserialize(input: Decoder): KPShapeType {
        val shapeType = KPShapeType.from(input.decodeString())
        if (shapeType === null) {
            throw Exception("Error in layerType deserialization")
        }
        return shapeType
    }
}