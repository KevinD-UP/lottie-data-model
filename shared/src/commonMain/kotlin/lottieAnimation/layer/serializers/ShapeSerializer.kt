package lottieAnimation.layer.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import lottieAnimation.layer.*

object ShapeSerializer : KSerializer<Shape> {
    override val descriptor: SerialDescriptor = Layer.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Shape) {
        when (value) {
            is ShapeEllipse -> encoder.encodeSerializableValue(ShapeEllipse.serializer(), value)
            is ShapeFill -> encoder.encodeSerializableValue(ShapeFill.serializer(), value)
            is ShapeGFill -> encoder.encodeSerializableValue(ShapeGFill.serializer(), value)
            is ShapeGStroke -> encoder.encodeSerializableValue(ShapeGStroke.serializer(), value)
            is ShapeGroup -> encoder.encodeSerializableValue(ShapeGroup.serializer(), value)
            is ShapeMerge -> encoder.encodeSerializableValue(ShapeMerge.serializer(), value)
            is ShapeRect -> encoder.encodeSerializableValue(ShapeRect.serializer(), value)
            is ShapeRound -> encoder.encodeSerializableValue(ShapeRound.serializer(), value)
            is ShapeShape -> encoder.encodeSerializableValue(ShapeShape.serializer(), value)
            is ShapeStar -> encoder.encodeSerializableValue(ShapeStar.serializer(), value)
            is ShapeStroke -> encoder.encodeSerializableValue(ShapeStroke.serializer(), value)
            is ShapeTransform -> encoder.encodeSerializableValue(ShapeTransform.serializer(), value)
            is ShapeTrim -> encoder.encodeSerializableValue(ShapeTrim.serializer(), value)
            else -> {}
        }
    }

    override fun deserialize(decoder: Decoder): Shape {
        val layer = decoder.decodeSerializableValue(JsonObject.serializer())

        return when (ShapeType.from(layer.jsonObject["ty"]?.jsonPrimitive?.content as String)) {
            ShapeType.ELLIPSE -> Json.decodeFromJsonElement(
                ShapeEllipse.serializer(),
                layer.jsonObject
            )
            ShapeType.FILL -> Json.decodeFromJsonElement(
                ShapeFill.serializer(),
                layer.jsonObject
            )
            ShapeType.G_FILL -> Json.decodeFromJsonElement(
                ShapeGFill.serializer(),
                layer.jsonObject
            )
            ShapeType.G_STROKE -> Json.decodeFromJsonElement(
                ShapeGStroke.serializer(),
                layer.jsonObject
            )
            ShapeType.GROUP -> Json.decodeFromJsonElement(
                ShapeGroup.serializer(),
                layer.jsonObject
            )
            ShapeType.MERGE -> Json.decodeFromJsonElement(
                ShapeMerge.serializer(),
                layer.jsonObject
            )
            ShapeType.RECT -> Json.decodeFromJsonElement(
                ShapeRect.serializer(),
                layer.jsonObject
            )
            ShapeType.ROUND -> Json.decodeFromJsonElement(
                ShapeRound.serializer(),
                layer.jsonObject
            )
            ShapeType.SHAPE -> Json.decodeFromJsonElement(
                ShapeShape.serializer(),
                layer.jsonObject
            )
            ShapeType.STAR -> Json.decodeFromJsonElement(
                ShapeStar.serializer(),
                layer.jsonObject
            )
            ShapeType.TRANSFORM -> Json.decodeFromJsonElement(
                ShapeTransform.serializer(),
                layer.jsonObject
            )
            ShapeType.TRIM -> Json.decodeFromJsonElement(
                ShapeTrim.serializer(),
                layer.jsonObject
            )

            else -> throw Exception("unknown Layer....${layer.jsonObject}")
        }
    }
}