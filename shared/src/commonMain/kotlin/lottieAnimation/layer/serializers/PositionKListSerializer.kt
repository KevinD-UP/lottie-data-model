package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.properties.MultiDimensionalK

object PositionKListSerializer : KSerializer<List<MultiDimensionalK>> {
    private val serializer = ListSerializer(PositionKSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: List<MultiDimensionalK>) {
        return serializer.serialize(encoder, obj)
    }

    override fun deserialize(decoder: Decoder): List<MultiDimensionalK> {
        return serializer.deserialize(decoder)
    }
}