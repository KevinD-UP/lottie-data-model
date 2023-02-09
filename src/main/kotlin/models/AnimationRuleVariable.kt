package io.kannelle.models

import kotlinx.serialization.Serializable

@Serializable
data class AnimationRuleVariable(
    val key: String,
    val value: String,
    val type: String,
    val description: String? = null,
    var result: String? = null) {

    companion object{
        const val TYPE_STRING = "string"
        const val TYPE_NUMBER = "number"
        const val TYPE_BOOLEAN = "boolean"
        const val TYPE_JSON_OBJECT = "jsonObject"
        const val TYPE_UNIT = "unit"
    }
}