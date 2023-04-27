package lottieAnimation.rules.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimationRuleVariable(
    val key: String,
    val value: String,
    val type: AnimationRuleVariableType,
    var description: String?)

@Serializable
enum class AnimationRuleVariableType {
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



