package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.properties.*

object MultiDimensionalListOrPrimitiveSerializer : KSerializer<MultiDimensionalListOrPrimitive> {
    private val serializer = ListSerializer(MultiDimensionalNodeSerializer)

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, obj: MultiDimensionalListOrPrimitive) {
        when (obj) {
            is MultiDimensionalList -> encoder.encodeSerializableValue(JsonArray.serializer(), listToJsonArray(list = obj.values))
            is MultiDimensionalPrimitive -> encoder.encodeSerializableValue(JsonPrimitive.serializer(), obj.value)
        }
    }

    override fun deserialize(decoder: Decoder): MultiDimensionalListOrPrimitive {
        val layer = decoder.decodeSerializableValue(JsonElement.serializer())
        return when (layer) {
            is JsonArray -> MultiDimensionalList(values = jsonArrayToList(jsonArray = layer.jsonArray))
            is JsonPrimitive -> MultiDimensionalPrimitive(value = layer.jsonPrimitive)
            else -> throw Exception("unknown MultiDimensionalListOrPrimitiveSerializer....")
        }
    }

    private fun jsonArrayToList(jsonArray: JsonArray): List<MultiDimensionalNodeObjectOrPrimitive> {
        return jsonArray.mapNotNull {
            when (it) {
                is JsonPrimitive -> MultiDimensionalNodePrimitive(value = it.jsonPrimitive)
                is JsonObject -> MultiDimensionNodeObject(
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

    private fun listToJsonArray(list: List<MultiDimensionalNodeObjectOrPrimitive>): JsonArray {
        val result = list.mapNotNull {
            when (it) {
                is MultiDimensionNodeObject -> Json.encodeToJsonElement(MultiDimensionNodeObject.serializer(), it)
                is MultiDimensionalNodePrimitive -> it.value
            }
        }
        return JsonArray(result)
    }
}