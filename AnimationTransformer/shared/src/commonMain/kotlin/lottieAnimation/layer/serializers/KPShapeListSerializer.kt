package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.KPShape


object KPShapeListSerializer : KSerializer<List<KPShape>> {
    private val serializer = ListSerializer(KPPShapeSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: List<KPShape>) {
        return serializer.serialize(encoder, obj)
    }

    override fun deserialize(decoder: Decoder): List<KPShape> {
        return serializer.deserialize(decoder)
    }
}