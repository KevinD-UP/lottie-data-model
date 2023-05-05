package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object KPMultiDimensionalListOrPrimitiveSerializer : KSerializer<KPMultiDimensionalListOrPrimitive> {
    private val serializer = ListSerializer(KPMultiDimensionalNodeSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: KPMultiDimensionalListOrPrimitive) {
        when (obj) {
            is KPMultiDimensionalList -> encoder.encodeSerializableValue(JsonArray.serializer(), listToJsonArray(list = obj.values))
            is KPMultiDimensionalPrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), obj.value)
        }
    }

    override fun deserialize(decoder: Decoder): KPMultiDimensionalListOrPrimitive {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (layer) {
            is JsonArray -> KPMultiDimensionalList(values = jsonArrayToList(jsonArray = layer.jsonArray))
            is JsonPrimitive -> KPMultiDimensionalPrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown MultiDimensionalListOrPrimitiveSerializer....")
        }
    }

    private fun jsonArrayToList(jsonArray: JsonArray): List<KPMultiDimensionalNodeObjectOrPrimitive> {
        return jsonArray.mapNotNull {
            when (it) {
                is JsonPrimitive -> KPMultiDimensionalNodePrimitive(value = it.jsonPrimitive)
                is JsonObject -> KPMultiDimensionNodeObject(
                    t = it.jsonObject["t"]?.jsonPrimitive,
                    s = it.jsonObject["s"]?.jsonArray,
                    i = it.jsonObject["i"]?.jsonObject,
                    o = it.jsonObject["o"]?.jsonObject,
                    h = it.jsonObject["h"]?.jsonPrimitive,
                    e = it.jsonObject["e"]?.jsonArray,
                    to = it.jsonObject["to"]?.jsonArray,
                    ti = it.jsonObject["ti"]?.jsonArray
                )
                else -> null
            }
        }
    }

    private fun listToJsonArray(list: List<KPMultiDimensionalNodeObjectOrPrimitive>): JsonArray {
        val result = list.mapNotNull {
            when (it) {
                is KPMultiDimensionNodeObject -> Json.encodeToJsonElement(KPMultiDimensionNodeObject.serializer(), it)
                is KPMultiDimensionalNodePrimitive -> it.value
            }
        }
        return JsonArray(result)
    }
}