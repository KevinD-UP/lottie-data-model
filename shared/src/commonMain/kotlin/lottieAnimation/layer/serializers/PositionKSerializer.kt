package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object PositionKSerializer : KSerializer<MultiDimensionalK> {
    override val descriptor: SerialDescriptor = MultiDimensionalK.serializer().descriptor

    override fun serialize(encoder: Encoder, value: MultiDimensionalK) {
        when (value) {
            is MultiDimensionObject -> encoder.encodeSerializableValue(MultiDimensionObject.serializer(), value)
            is MultiDimensionalKPrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), value.value)
        }
    }

    override fun deserialize(decoder: Decoder): MultiDimensionalK {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (layer) {
            is JsonObject -> Json.decodeFromJsonElement(
                MultiDimensionObject.serializer(),
                layer.jsonObject
            )
            is JsonPrimitive -> MultiDimensionalKPrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown PositionK Layer....")
        }
    }
}