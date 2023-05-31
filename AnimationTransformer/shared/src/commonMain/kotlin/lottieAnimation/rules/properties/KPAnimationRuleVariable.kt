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
    val transformIndexForList: Int?,
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
    @SerialName("position")
    POSITION,
    @SerialName("position_s")
    POSITION_S,
    @SerialName("position_e")
    POSITION_E,
    @SerialName("position_it")
    POSITION_IT,
    @SerialName("frame")
    FRAME,
    @SerialName("frame_it")
    FRAME_IT
}



