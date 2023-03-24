package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import lottieAnimation.layer.Layer

object LayerListSerializer : KSerializer<List<Layer>> {
    private val serializer = ListSerializer(LayerSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: List<Layer>) {
        return serializer.serialize(encoder, obj)
    }

    override fun deserialize(decoder: Decoder): List<Layer> {
        return serializer.deserialize(decoder)
    }
}