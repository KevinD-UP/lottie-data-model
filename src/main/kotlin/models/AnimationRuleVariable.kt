package io.kannelle.models

data class AnimationRuleVariable(
    val key: String,
    val value: String,
    val type: String,
    var result: String?) {

    companion object{
        const val TYPE_STRING = "string"
        const val TYPE_NUMBER = "number"
        const val TYPE_BOOLEAN = "boolean"
        const val TYPE_JSON_OBJECT = "jsonObject"
        const val TYPE_UNIT = "unit"
    }
}