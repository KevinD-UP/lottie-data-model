package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object PositionKSerializer : KSerializer<PositionK> {
    override val descriptor: SerialDescriptor = PositionK.serializer().descriptor

    override fun serialize(encoder: Encoder, value: PositionK) {
        when (value) {
            is PositionKeyframe -> encoder.encodeSerializableValue(PositionKeyframe.serializer(), value)
            is PositionKPrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), value.value)
        }
    }

    override fun deserialize(decoder: Decoder): PositionK {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (layer) {
            is JsonObject -> Json.decodeFromJsonElement(
                PositionKeyframe.serializer(),
                layer.jsonObject
            )
            is JsonPrimitive -> PositionKPrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown PositionK Layer....")
        }
    }
}