package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.*

object KPPShapeSerializer : KSerializer<KPShape> {
    override val descriptor: SerialDescriptor = KPLayer.serializer().descriptor

    override fun serialize(encoder: Encoder, value: KPShape) {
        when (value) {
            is KPShapeEllipse -> encoder.encodeSerializableValue(KPShapeEllipse.serializer(), value)
            is KPShapeFill -> encoder.encodeSerializableValue(KPShapeFill.serializer(), value)
            is KPShapeGFill -> encoder.encodeSerializableValue(KPShapeGFill.serializer(), value)
            is KPShapeGStroke -> encoder.encodeSerializableValue(KPShapeGStroke.serializer(), value)
            is KPShapeGroup -> encoder.encodeSerializableValue(KPShapeGroup.serializer(), value)
            is KPShapeMerge -> encoder.encodeSerializableValue(KPShapeMerge.serializer(), value)
            is KPShapeRect -> encoder.encodeSerializableValue(KPShapeRect.serializer(), value)
            is KPShapeRound -> encoder.encodeSerializableValue(KPShapeRound.serializer(), value)
            is KPShapeShape -> encoder.encodeSerializableValue(KPShapeShape.serializer(), value)
            is KPShapeStar -> encoder.encodeSerializableValue(KPShapeStar.serializer(), value)
            is KPShapeStroke -> encoder.encodeSerializableValue(KPShapeStroke.serializer(), value)
            is KPShapeTransform -> encoder.encodeSerializableValue(KPShapeTransform.serializer(), value)
            is KPShapeTrim -> encoder.encodeSerializableValue(KPShapeTrim.serializer(), value)
            else -> {}
        }
    }

    override fun deserialize(decoder: Decoder): KPShape {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())

        return when (KPShapeType.from(layer.jsonObject["ty"]?.jsonPrimitive?.content as String)) {
            KPShapeType.ELLIPSE -> Json.decodeFromJsonElement(
                KPShapeEllipse.serializer(),
                layer.jsonObject
            )
            KPShapeType.FILL -> Json.decodeFromJsonElement(
                KPShapeFill.serializer(),
                layer.jsonObject
            )
            KPShapeType.G_FILL -> Json.decodeFromJsonElement(
                KPShapeGFill.serializer(),
                layer.jsonObject
            )
            KPShapeType.G_STROKE -> Json.decodeFromJsonElement(
                KPShapeGStroke.serializer(),
                layer.jsonObject
            )
            KPShapeType.GROUP -> Json.decodeFromJsonElement(
                KPShapeGroup.serializer(),
                layer.jsonObject
            )
            KPShapeType.MERGE -> Json.decodeFromJsonElement(
                KPShapeMerge.serializer(),
                layer.jsonObject
            )
            KPShapeType.RECT -> Json.decodeFromJsonElement(
                KPShapeRect.serializer(),
                layer.jsonObject
            )
            KPShapeType.ROUND -> Json.decodeFromJsonElement(
                KPShapeRound.serializer(),
                layer.jsonObject
            )
            KPShapeType.SHAPE -> Json.decodeFromJsonElement(
                KPShapeShape.serializer(),
                layer.jsonObject
            )
            KPShapeType.STAR -> Json.decodeFromJsonElement(
                KPShapeStar.serializer(),
                layer.jsonObject
            )
            KPShapeType.TRANSFORM -> Json.decodeFromJsonElement(
                KPShapeTransform.serializer(),
                layer.jsonObject
            )
            KPShapeType.TRIM -> Json.decodeFromJsonElement(
                KPShapeTrim.serializer(),
                layer.jsonObject
            )
            KPShapeType.STROKE -> Json.decodeFromJsonElement(
                KPShapeStroke.serializer(),
                layer.jsonObject
            )

            else -> throw Exception("unknown Shape....${layer.jsonObject}")
        }
    }
}