package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object KPMultiDimensionalNodeSerializer : KSerializer<KPMultiDimensionalNodeObjectOrPrimitive> {
    override val descriptor: SerialDescriptor = KPMultiDimensionalNodeObjectOrPrimitive.serializer().descriptor

    override fun serialize(encoder: Encoder, value: KPMultiDimensionalNodeObjectOrPrimitive) {
        when (value) {
            is KPMultiDimensionNodeObject -> encoder.encodeSerializableValue(KPMultiDimensionNodeObject.serializer(), value)
            is KPMultiDimensionalNodePrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), value.value)
        }
    }

    override fun deserialize(decoder: Decoder): KPMultiDimensionalNodeObjectOrPrimitive {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (decoder.decodeSerializableValue(JsonElement.serializer())) {
            is JsonObject -> Json.decodeFromJsonElement(
                KPMultiDimensionNodeObject.serializer(),
                layer.jsonObject
            )
            is JsonPrimitive -> KPMultiDimensionalNodePrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown MultiDimensionalNodeObjectOrPrimitive....")
        }
    }
}