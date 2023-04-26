package lottieAnimation.rules.properties

import kotlinx.serialization.Serializable

@Serializable
data class AnimationRuleVariable(
    val key: String,
    val value: String,
    val type: AnimationRuleVariableType,
    var description: String?)

@Serializable
enum class AnimationRuleVariableType {
    STRING,
    NUMBER,
    BOOLEAN,
    JSON_OBJECT,
    UNIT
}



