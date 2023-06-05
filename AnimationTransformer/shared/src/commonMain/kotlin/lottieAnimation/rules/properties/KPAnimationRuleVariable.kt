package lottieAnimation.rules.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KPAnimationRuleVariable(
    val key: String?,
    val value: String,
    val transformNodes: List<KPAnimationRuleVariableTransformNode>?,
    val type: KPAnimationRuleVariableType,
    val description: String?
)

@Serializable
data class KPAnimationRuleVariableTransformNode(
    val ind: Int?,
    val layerType: Int?,
    val transformType: KPAnimationRuleTransformType?,
    val transformNodeVIndex: Int?,
    val transformKey: String?,
    val description: String?,
)

@Serializable
enum class KPAnimationRuleVariableType {
    @SerialName("string")
    STRING,

    @SerialName("number")
    NUMBER,

    @SerialName("boolean")
    BOOLEAN,

    @SerialName("jsonObject")
    JSON_OBJECT,

    @SerialName("unit")
    UNIT
}

@Serializable
enum class KPAnimationRuleTransformType {
    @SerialName("unit")
    UNIT,
    @SerialName("position_x")
    POSITION_X,
    @SerialName("position_y")
    POSITION_Y,
    @SerialName("position_start_x")
    POSITION_START_X,
    @SerialName("position_start_y")
    POSITION_START_Y,
    @SerialName("position_end_x")
    POSITION_END_X,
    @SerialName("position_end_y")
    POSITION_END_Y,
    @SerialName("position_it_x")
    POSITION_IT_X,
    @SerialName("position_it_y")
    POSITION_IT_Y,
    @SerialName("frame_width")
    FRAME_WIDTH,
    @SerialName("frame_height")
    FRAME_HEIGHT,
    @SerialName("frame_it_width")
    FRAME_IT_WIDTH,
    @SerialName("frame_it_height")
    FRAME_IT_HEIGHT,
    @SerialName("source_width")
    SOURCE_W,
    @SerialName("source_height")
    SOURCE_H,
    @SerialName("anchor_x")
    ANCHOR_X,
    @SerialName("anchor_y")
    ANCHOR_Y,
    @SerialName("out_point")
    OUT_POINT,
    @SerialName("time")
    TIME
}



