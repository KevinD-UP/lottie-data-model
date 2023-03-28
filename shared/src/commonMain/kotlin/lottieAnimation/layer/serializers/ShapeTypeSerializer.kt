package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.ShapeType

object ShapeTypeSerializer : KSerializer<ShapeType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("layerType", PrimitiveKind.INT)

    override fun serialize(output: Encoder, obj: ShapeType) {
        output.encodeString(obj.toString())
    }

    override fun deserialize(input: Decoder): ShapeType {
        val shapeType = ShapeType.from(input.decodeString())
        if (shapeType === null) {
            throw Exception("Error in layerType deserialization")
        }
        return shapeType
    }
}