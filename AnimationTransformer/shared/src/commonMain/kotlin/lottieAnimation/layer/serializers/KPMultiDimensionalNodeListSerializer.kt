package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.properties.KPMultiDimensionalNodeObjectOrPrimitive

object KPMultiDimensionalNodeListSerializer : KSerializer<List<KPMultiDimensionalNodeObjectOrPrimitive>> {
    private val serializer = ListSerializer(KPMultiDimensionalNodeSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: List<KPMultiDimensionalNodeObjectOrPrimitive>) {
        return serializer.serialize(encoder, obj)
    }

    override fun deserialize(decoder: Decoder): List<KPMultiDimensionalNodeObjectOrPrimitive> {
        return serializer.deserialize(decoder)
    }
}