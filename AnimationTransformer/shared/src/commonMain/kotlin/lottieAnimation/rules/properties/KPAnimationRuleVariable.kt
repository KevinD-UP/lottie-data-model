package lottieAnimation.rules.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KPAnimationRuleVariable(
    val key: String,
    val value: String,
    val type: KPAnimationRuleVariableType,
    val description: String?
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



