package lottieAnimation.layer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable(with = TextJustifySerializer::class)
enum class TextJustify {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY_WITH_LAST_LINE_LEFT,
    JUSTIFY_WITH_LAST_LINE_RIGHT,
    JUSTIFY_WITH_LAST_LINE_CENTER,
    JUSTIFY_WITH_LAST_LINE_FULL;

    companion object {
        infix fun from(value: Int): TextJustify? = TextJustify.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable(with = TextCapsSerializer::class)
enum class TextCaps {
    REGULAR,
    ALL_CAPS,
    SMALL_CAPS;

    companion object {
        infix fun from(value: Int): TextCaps? = TextCaps.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable
data class TextDocumentKeyframe(
    /**
     * Start
     */
    val s: TextDocument,

    /**
     * Time
     */
    val t: Int
)

@Serializable
data class AnimatedTextDocument(
    /**
     * Keyframes
     */
    val k: List<TextDocumentKeyframe>,

    /**
     * Expression
     */
    val x: String? = null
)

@Serializable
data class TextDocument(
    /**
     * Font Family
     */
    val f: String,

    /**
     * Fill Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    val fc: List<Int>,

    /**
     * Stroke Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    val sc: List<Int>? = null,

    /**
     * Stroke Width
     */
    val sw: Int? = null,

    /**
     * Stroke Over Fill
     * Render stroke above the fill
     */
    val of: Boolean? = null,

    /**
     * Font Size
     */
    val s: Int? = null,

    /**
     * Line Height
     * Distance between lines on multiline or wrapped text
     */
    val lh: Int? = null,

    /**
     * Wrap Size
     * Size of the box containing the text
     * array of 2 items
     */
    val sz: List<Int>? = null,

    /**
     * Wrap position
     * Position of the box containing the text
     * array of 2 items
     */
    val ps: List<Int>? = null,

    /**
     * Text
     * Text, note that newlines are encoded with \r
     */
    val t: String,

    /**
     * Text Justify
     */
    val j: TextJustify? = null,

    /**
     * Text Caps
     */
    val ca: TextCaps? = null,

    /**
     * Text Tracking
     */
    val tr: Int? = null,

    /**
     * Baseline Shift
     */
    val ls: Int? = null
)

@Serializable
data class TextData(
    /**
     * Ranges
     */
    val a: JsonArray,

    /**
     * Document
     */
    val d: AnimatedTextDocument,

    /**
     * Alignment
     */
    val m: JsonElement,

    /**
     * Follow Path
     */
    val p: JsonElement
)

object TextCapsSerializer : KSerializer<TextCaps> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextCaps) {
        encoder.encodeInt(value.ordinal)
    }

    override fun deserialize(decoder: Decoder): TextCaps {
        val textCap = TextCaps.from(decoder.decodeInt())
        if (textCap === null) {
            throw Exception("Error in textCaps deserialization")
        }
        return textCap
    }

}

object TextJustifySerializer : KSerializer<TextJustify> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextJustify) {
        encoder.encodeInt(value.ordinal)
    }

    override fun deserialize(decoder: Decoder): TextJustify {
        val textJustify = TextJustify.from(decoder.decodeInt())
        if (textJustify === null) {
            throw Exception("Error in textJustify deserialization")
        }
        return textJustify
    }

}