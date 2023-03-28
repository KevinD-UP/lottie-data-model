package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import lottieAnimation.layer.properties.MultiDimensional
import lottieAnimation.layer.properties.MultiDimensionalKeyframed
import lottieAnimation.layer.properties.Position

object PositionSerializer : KSerializer<Position> {
    override val descriptor: SerialDescriptor = Position.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Position) {
        when (value) {
            is MultiDimensional -> encoder.encodeSerializableValue(MultiDimensional.serializer(), value)
            is MultiDimensionalKeyframed -> encoder.encodeSerializableValue(MultiDimensionalKeyframed.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): Position {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())

        return if (layer.jsonObject["ti"] != null && layer.jsonObject["to"] != null) {
            Json.decodeFromJsonElement(
                MultiDimensionalKeyframed.serializer(),
                layer.jsonObject
            )
        } else {
            Json.decodeFromJsonElement(
                MultiDimensional.serializer(),
                layer.jsonObject
            )
        }
    }
}