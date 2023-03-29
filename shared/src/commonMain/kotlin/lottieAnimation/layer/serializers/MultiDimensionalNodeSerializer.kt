package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object MultiDimensionalNodeSerializer : KSerializer<MultiDimensionalNodeObjectOrPrimitive> {
    override val descriptor: SerialDescriptor = MultiDimensionalNodeObjectOrPrimitive.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MultiDimensionalNodeObjectOrPrimitive) {
        when (value) {
            is MultiDimensionNodeObject -> encoder.encodeSerializableValue(MultiDimensionNodeObject.serializer(), value)
            is MultiDimensionalNodePrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), value.value)
        }
    }

    override fun deserialize(decoder: Decoder): MultiDimensionalNodeObjectOrPrimitive {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (decoder.decodeSerializableValue(JsonElement.serializer())) {
            is JsonObject -> Json.decodeFromJsonElement(
                MultiDimensionNodeObject.serializer(),
                layer.jsonObject
            )
            is JsonPrimitive -> MultiDimensionalNodePrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown MultiDimensionalNodeObjectOrPrimitive....")
        }
    }
}