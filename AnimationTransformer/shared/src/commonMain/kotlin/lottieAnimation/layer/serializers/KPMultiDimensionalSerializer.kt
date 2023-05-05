package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import lottieAnimation.layer.properties.KPMultiDimensionalSimple
import lottieAnimation.layer.properties.KPMultiDimensionalKeyframed
import lottieAnimation.layer.properties.KPMultiDimensional

object KPMultiDimensionalSerializer : KSerializer<KPMultiDimensional> {
    override val descriptor: SerialDescriptor = KPMultiDimensional.serializer().descriptor

    override fun serialize(encoder: Encoder, value: KPMultiDimensional) {
        when (value) {
            is KPMultiDimensionalSimple -> encoder.encodeSerializableValue(KPMultiDimensionalSimple.serializer(), value)
            is KPMultiDimensionalKeyframed -> encoder.encodeSerializableValue(KPMultiDimensionalKeyframed.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): KPMultiDimensional {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())
        return if (layer.jsonObject["ti"] != null && layer.jsonObject["to"] != null) {
            Json.decodeFromJsonElement(
                KPMultiDimensionalKeyframed.serializer(),
                layer.jsonObject
            )
        } else {
            Json.decodeFromJsonElement(
                KPMultiDimensionalSimple.serializer(),
                layer.jsonObject
            )
        }
    }
}