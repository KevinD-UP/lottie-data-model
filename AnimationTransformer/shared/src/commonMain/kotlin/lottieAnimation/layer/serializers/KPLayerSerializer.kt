package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.*

object KPLayerSerializer : KSerializer<KPLayer> {
    override val descriptor: SerialDescriptor = KPLayer.serializer().descriptor

    override fun serialize(encoder: Encoder, value: KPLayer) {
        when (value) {
            is KPPrecompositionLayer -> encoder.encodeSerializableValue(KPPrecompositionLayer.serializer(), value)
            is KPSolidLayer -> encoder.encodeSerializableValue(KPSolidLayer.serializer(), value)
            is KPImageLayer -> encoder.encodeSerializableValue(KPImageLayer.serializer(), value)
            is KPNullLayer -> encoder.encodeSerializableValue(KPNullLayer.serializer(), value)
            is KPShapeLayer -> encoder.encodeSerializableValue(KPShapeLayer.serializer(), value)
            is KPTextLayer -> encoder.encodeSerializableValue(KPTextLayer.serializer(), value)
            is KPAudioLayer -> encoder.encodeSerializableValue(KPAudioLayer.serializer(), value)
            is KPCameraLayer -> encoder.encodeSerializableValue(KPCameraLayer.serializer(), value)
            is KPDataLayer -> encoder.encodeSerializableValue(KPDataLayer.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): KPLayer {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())

        return when (KPLayerType.from(layer.jsonObject["ty"]?.jsonPrimitive?.int as Int)) {
            KPLayerType.PRECOMP_LAYER -> Json.decodeFromJsonElement(
                KPPrecompositionLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.SOLID_LAYER -> Json.decodeFromJsonElement(
                KPSolidLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.IMAGE_LAYER -> Json.decodeFromJsonElement(
                KPImageLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.NULL_LAYER -> Json.decodeFromJsonElement(
                KPNullLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.SHAPE_LAYER -> Json.decodeFromJsonElement(
                KPShapeLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.TEXT_LAYER -> Json.decodeFromJsonElement(
                KPTextLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.AUDIO_LAYER -> Json.decodeFromJsonElement(
                KPAudioLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.CAMERA_LAYER -> Json.decodeFromJsonElement(
                KPCameraLayer.serializer(),
                layer.jsonObject
            )
            KPLayerType.DATA_LAYER -> Json.decodeFromJsonElement(
                KPDataLayer.serializer(),
                layer.jsonObject
            )
            else -> throw Exception("unknown Layer....")
        }
    }
}