package lottieAnimation.layer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import lottieAnimation.layer.properties.KPMultiDimensionalListOrPrimitive
import lottieAnimation.layer.serializers.KPMultiDimensionalListOrPrimitiveSerializer

@Serializable(with = KPTextJustifySerializer::class)
enum class KPTextJustify {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY_WITH_LAST_LINE_LEFT,
    JUSTIFY_WITH_LAST_LINE_RIGHT,
    JUSTIFY_WITH_LAST_LINE_CENTER,
    JUSTIFY_WITH_LAST_LINE_FULL;

    companion object {
        infix fun from(value: Int): KPTextJustify? =
            KPTextJustify.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable(with = KPTextCapsSerializer::class)
enum class KPTextCaps {
    REGULAR,
    ALL_CAPS,
    SMALL_CAPS;

    companion object {
        infix fun from(value: Int): KPTextCaps? =
            KPTextCaps.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable
data class KPTextDocumentKeyframe(
    /**
     * Start
     */
    var s: KPTextDocument,

    /**
     * Time
     */
    var t: JsonElement
)

@Serializable
data class KPAnimatedTextDocument(
    /**
     * Keyframes
     */
    var k: List<KPTextDocumentKeyframe>,

    /**
     * Expression
     */
    var x: String? = null
)

@Serializable
data class KPTextDocument(
    /**
     * Font Family
     */
    var f: String,

    /**
     * Fill Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    var fc: List<JsonElement>,

    /**
     * Stroke Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    var sc: List<JsonElement>? = null,

    /**
     * Stroke Width
     */
    var sw: JsonElement? = null,

    /**
     * Stroke Over Fill
     * Render stroke above the fill
     */
    var of: Boolean? = null,

    /**
     * Font Size
     */
    var s: JsonElement? = null,

    /**
     * Line Height
     * Distance between lines on multiline or wrapped text
     */
    var lh: JsonElement? = null,

    /**
     * Wrap Size
     * Size of the box containing the text
     * array of 2 items
     */
    var sz: List<JsonElement>? = null,

    /**
     * Wrap position
     * Position of the box containing the text
     * array of 2 items
     */
    var ps: List<JsonElement>? = null,

    /**
     * Text
     * Text, note that newlines are encoded with \r
     */
    var t: String,

    /**
     * Text Justify
     */
    var j: KPTextJustify? = null,

    /**
     * Text Caps
     */
    var ca: KPTextCaps? = null,

    /**
     * Text Tracking
     */
    var tr: JsonElement? = null,

    /**
     * Baseline Shift
     */
    var ls: JsonElement? = null
)

@Serializable
data class KPTextData(
    /**
     * Ranges
     */
    var a: List<KPTextRange>,

    /**
     * Document
     */
    var d: KPAnimatedTextDocument,

    /**
     * Alignment
     */
    var m: JsonElement,

    /**
     * Follow Path
     */
    var p: JsonElement
)

@Serializable
data class KPTextRange(
    /**
     * name of the animation
     */
    var nm: JsonPrimitive? = null,
    /**
     * scale
     */
    var s: JsonObject? = null,
    /**
     * Addition animation properties
     */
    var a: KPTextAdditionalAnimationProperties
)

@Serializable
data class KPTextAdditionalAnimationProperties(
    var s: JsonObject? = null,
    /**
     * Fill Color
     */
    var fc: KPTextFillColor? = null,

    var o: JsonElement? = null,

    var p: JsonElement? = null,

    var t: JsonElement? = null,

    var ls: JsonElement? = null,

    var sw: JsonElement? = null
)

@Serializable
data class KPTextFillColor(
    /**
     * Animated
     */
    var a: JsonPrimitive,

    /**
     * KeyFrames: Could be opacity(Primitive) or Color(List)
     */
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    var k: KPMultiDimensionalListOrPrimitive,
    /**
     * Property index
     */
    var ix: JsonPrimitive
)

@Serializable
data class KPTextEffect(
    /**
     * Effect type
     */
    val ty: Int,

    val nm: JsonPrimitive? = null,

    val mn: JsonPrimitive? = null,

    val ix: JsonPrimitive? = null,

    val np: JsonPrimitive? = null,

    val en: JsonPrimitive? = null,

    /**
     * SubEffect
     */
    var ef: List<KPTextSubEffect>

)

@Serializable
data class KPTextSubEffect(
    /**
     * SubEffect Type
     */
    val ty: Int,
    /**
     * Effect Value
     */

    val nm: JsonPrimitive? = null,

    val mn: JsonPrimitive? = null,

    val ix: JsonPrimitive? = null,

    var v: KPTextSubEffectValue
)

@Serializable
data class KPTextSubEffectValue(
    /**
     * takes 0 or 1 weather is is animated or not
     */
    val a: JsonPrimitive? = null,
    /**
     * Color: Could be opacity(Primitive) or Color(List)
     */
    @Serializable(with = KPMultiDimensionalListOrPrimitiveSerializer::class)
    var k: KPMultiDimensionalListOrPrimitive,
    /**
     * index position
     */
    val ix: JsonPrimitive? = null,

    var x: JsonElement? = null
)

object KPTextCapsSerializer : KSerializer<KPTextCaps> {
    override var descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: KPTextCaps) {
        encoder.encodeInt(value.ordinal)
    }

    override fun deserialize(decoder: Decoder): KPTextCaps {
        var textCap = KPTextCaps.from(decoder.decodeInt())
        if (textCap === null) {
            throw Exception("Error in textCaps deserialization")
        }
        return textCap
    }

}

object KPTextJustifySerializer : KSerializer<KPTextJustify> {
    override var descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: KPTextJustify) {
        encoder.encodeInt(value.ordinal)
    }

    override fun deserialize(decoder: Decoder): KPTextJustify {
        val textJustify = KPTextJustify.from(decoder.decodeInt())
        if (textJustify === null) {
            throw Exception("Error in textJustify deserialization")
        }
        return textJustify
    }
}