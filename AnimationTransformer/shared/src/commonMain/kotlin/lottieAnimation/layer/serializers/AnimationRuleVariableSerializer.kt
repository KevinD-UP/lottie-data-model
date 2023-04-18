package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AnimationRuleVariableSerializer : KSerializer<AnimationRuleVariable> {

    override val descriptor: SerialDescriptor = AnimationRuleVariable.serializer().descriptor
    override fun serialize(encoder: Encoder, value: AnimationRuleVariable) {
        encoder.encodeString(value.key)
        encoder.encodeString(value.value)
        encoder.encodeString(value.type)
        encoder.encodeNullableSerializableValue(String.serializer(), value.result)
    }

    override fun deserialize(decoder: Decoder): AnimationRuleVariable {
        val json = decoder.decodeJsonElement() as JsonObject
        val key = json["key"]!!.jsonPrimitive.content
        val value = json["value"]!!.jsonPrimitive.content
        val type = json["type"]!!.jsonPrimitive.content
        val result = json["result"]?.jsonPrimitive?.contentOrNull
        return AnimationRuleVariable(key, value, type, result)
    }
}