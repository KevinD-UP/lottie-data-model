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
        infix fun from(value: Int): KPTextJustify? = KPTextJustify.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable(with = KPTextCapsSerializer::class)
enum class KPTextCaps {
    REGULAR,
    ALL_CAPS,
    SMALL_CAPS;

    companion object {
        infix fun from(value: Int): KPTextCaps? = KPTextCaps.values().firstOrNull { v -> v.ordinal == value }
    }
}

@Serializable
data class KPTextDocumentKeyframe(
    /**
     * Start
     */
    val s: KPTextDocument,

    /**
     * Time
     */
    val t: JsonElement
)

@Serializable
data class KPAnimatedTextDocument(
    /**
     * Keyframes
     */
    val k: List<KPTextDocumentKeyframe>,

    /**
     * Expression
     */
    val x: String? = null
)

@Serializable
data class KPTextDocument(
    /**
     * Font Family
     */
    val f: String,

    /**
     * Fill Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    val fc: List<JsonElement>,

    /**
     * Stroke Color
     * Color as a [r, g, b] array with values in [0, 1] range
     * array of size >=3 and <= 4
     */
    val sc: List<JsonElement>? = null,

    /**
     * Stroke Width
     */
    val sw: JsonElement? = null,

    /**
     * Stroke Over Fill
     * Render stroke above the fill
     */
    val of: Boolean? = null,

    /**
     * Font Size
     */
    val s: JsonElement? = null,

    /**
     * Line Height
     * Distance between lines on multiline or wrapped text
     */
    val lh: JsonElement? = null,

    /**
     * Wrap Size
     * Size of the box containing the text
     * array of 2 items
     */
    val sz: List<JsonElement>? = null,

    /**
     * Wrap position
     * Position of the box containing the text
     * array of 2 items
     */
    val ps: List<JsonElement>? = null,

    /**
     * Text
     * Text, note that newlines are encoded with \r
     */
    val t: String,

    /**
     * Text Justify
     */
    val j: KPTextJustify? = null,

    /**
     * Text Caps
     */
    val ca: KPTextCaps? = null,

    /**
     * Text Tracking
     */
    val tr: JsonElement? = null,

    /**
     * Baseline Shift
     */
    val ls: JsonElement? = null
)

@Serializable
data class KPTextData(
    /**
     * Ranges
     */
    val a: JsonArray,

    /**
     * Document
     */
    val d: KPAnimatedTextDocument,

    /**
     * Alignment
     */
    val m: JsonElement,

    /**
     * Follow Path
     */
    val p: JsonElement
)

object KPTextCapsSerializer : KSerializer<KPTextCaps> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: KPTextCaps) {
        encoder.encodeInt(value.ordinal)
    }

    override fun deserialize(decoder: Decoder): KPTextCaps {
        val textCap = KPTextCaps.from(decoder.decodeInt())
        if (textCap === null) {
            throw Exception("Error in textCaps deserialization")
        }
        return textCap
    }

}

object KPTextJustifySerializer : KSerializer<KPTextJustify> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("textCaps", PrimitiveKind.INT)

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