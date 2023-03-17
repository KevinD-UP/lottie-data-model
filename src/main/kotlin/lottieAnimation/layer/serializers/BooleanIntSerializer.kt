package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.BooleanInt

object BooleanIntSerializer : KSerializer<BooleanInt> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("booleanType", PrimitiveKind.INT)

    override fun serialize(output: Encoder, obj: BooleanInt) {
        output.encodeInt(obj.ordinal)
    }

    override fun deserialize(input: Decoder): BooleanInt {
        val booleanType = BooleanInt.from(input.decodeInt())
        if (booleanType === null) {
            throw Exception("Error in booleanType deserialization")
        }
        return booleanType
    }
}