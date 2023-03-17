package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.*

object LayerSerializer : KSerializer<Layer> {
    override val descriptor: SerialDescriptor = Layer.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Layer) {
        when (value) {
            is PrecompositionLayer -> encoder.encodeSerializableValue(PrecompositionLayer.serializer(), value)
            is SolidColorLayer -> encoder.encodeSerializableValue(SolidColorLayer.serializer(), value)
            is ImageLayer -> encoder.encodeSerializableValue(ImageLayer.serializer(), value)
            is NullLayer -> encoder.encodeSerializableValue(NullLayer.serializer(), value)
            is ShapeLayer -> encoder.encodeSerializableValue(ShapeLayer.serializer(), value)
            is TextLayer -> encoder.encodeSerializableValue(TextLayer.serializer(), value)
            is AudioLayer -> encoder.encodeSerializableValue(AudioLayer.serializer(), value)
            is CameraLayer -> encoder.encodeSerializableValue(CameraLayer.serializer(), value)
            is DataLayer -> encoder.encodeSerializableValue(DataLayer.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): Layer {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())

        return when (LayerType.from(layer.jsonObject["ty"]?.jsonPrimitive?.int as Int)) {
            LayerType.PRECOMP_LAYER -> Json.decodeFromJsonElement(
                PrecompositionLayer.serializer(),
                layer.jsonObject
            )
            LayerType.SOLID_COLOR_LAYER -> Json.decodeFromJsonElement(
                SolidColorLayer.serializer(),
                layer.jsonObject
            )
            LayerType.IMAGE_LAYER -> Json.decodeFromJsonElement(
                ImageLayer.serializer(),
                layer.jsonObject
            )
            LayerType.NULL_LAYER -> Json.decodeFromJsonElement(
                NullLayer.serializer(),
                layer.jsonObject
            )
            LayerType.SHAPE_LAYER -> Json.decodeFromJsonElement(
                ShapeLayer.serializer(),
                layer.jsonObject
            )
            LayerType.TEXT_LAYER -> Json.decodeFromJsonElement(
                TextLayer.serializer(),
                layer.jsonObject
            )
            LayerType.AUDIO_LAYER -> Json.decodeFromJsonElement(
                AudioLayer.serializer(),
                layer.jsonObject
            )
            LayerType.CAMERA_LAYER -> Json.decodeFromJsonElement(
                CameraLayer.serializer(),
                layer.jsonObject
            )
            LayerType.DATA_LAYER -> Json.decodeFromJsonElement(
                DataLayer.serializer(),
                layer.jsonObject
            )
            else -> throw Exception("unknown Layer....")
        }
    }
}