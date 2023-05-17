package transformer

import expressionParser.KPProjectExpressionParser
import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.*
import lottieAnimation.rules.properties.KPAnimationRuleTransformType
import lottieAnimation.rules.properties.KPAnimationRuleVariable
import lottieAnimation.rules.properties.KPAnimationRules

class KPVariableTransformer(
    private val delegate: KPAnimationTransformerFunctionsDelegate
) {
    fun transformVariables(
        animation: KPLottieAnimation,
        animationRules: KPAnimationRules
    )
            : KPLottieAnimation {
        val expressionParser = KPProjectExpressionParser(
            animation = animation,
            functionsDelegate = delegate,
            projectWidth = 1920.0,
            projectHeight = 1080.0,
            subtitleHeight = 0.0,
            watermarkHeight = 0.0
        )
        var animationResultWrapper = KPLottieAnimationWrapper(animation = animation.copy())
        val variables = animationRules.variables ?: return animationResultWrapper.animation
        variables.forEach { variable ->
            println("variable ind = ${variable.ind} - transformation = ${variable.transformType}")
            animation.layers.find { it.ind == variable.ind }?.let { layer ->
                animationResultWrapper.applyVariableOnTextLayer(
                    layer = layer,
                    variable = variable,
                    expressionParser = expressionParser
                )
                animationResultWrapper.applyVariableOnShapeLayer(
                    layer = layer,
                    variable = variable,
                    expressionParser = expressionParser
                )
            }
        }
        return animationResultWrapper.animation
    }
}

class KPLottieAnimationWrapper(var animation: KPLottieAnimation) {}

fun KPLottieAnimationWrapper.applyVariableOnTextLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    expressionParser: KPProjectExpressionParser
) {
    // Return if the layer is not a KPTextLayer
    if (layer !is KPTextLayer) return

    when (variable.transformType) {
        KPAnimationRuleTransformType.POSITION -> {
            val expressionResult =
                expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
            println("Expression: ${variable.value}")
            println("Expression result: $expressionResult")
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = variable.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        if (transformIndexForList < list.count()) {
                            list[transformIndexForList] = KPMultiDimensionalNodePrimitive(
                                value = JsonPrimitive(expressionResult)
                            )
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    is KPMultiDimensionalPrimitive -> {
                        KPMultiDimensionalPrimitive(
                            value = JsonPrimitive(expressionResult)
                        )
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        else -> {
            // Handle other transform types if necessary
        }
    }
}

fun KPLottieAnimationWrapper.applyVariableOnShapeLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    expressionParser: KPProjectExpressionParser
) {
    if (layer !is KPShapeLayer) {
        return
    }
    val expressionResult =
        expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
    println("Expression: ${variable.value}")
    println("Expression result: $expressionResult")

    when (variable.transformType) {
        KPAnimationRuleTransformType.POSITION -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = variable.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        if (transformIndexForList < list.count()) {
                            list[transformIndexForList] = KPMultiDimensionalNodePrimitive(
                                value = JsonPrimitive(expressionResult)
                            )
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    is KPMultiDimensionalPrimitive -> {
                        KPMultiDimensionalPrimitive(
                            value = JsonPrimitive(expressionResult)
                        )
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_IT -> {
            val shape = layer.shapes?.firstOrNull { it.key == variable.transformKey } ?: return
            when (shape) {
                is KPShapeGroup -> {
                    println("variable.transformSubKey = ${variable.transformSubKey}")
                    println("shape it ${shape.it.firstOrNull { it.key == variable.transformSubKey }}")
                    shape.it.firstOrNull { it.key == variable.transformSubKey }?.let {
                        when (it) {
                            is KPShapeShape -> {
                                it.ks?.jsonObject?.let { ks ->
                                    ks["k"]?.jsonObject?.let { k ->
                                        k["v"]?.jsonArray?.let { v ->
                                            v.getOrNull(1)?.jsonArray?.let { innerArray ->
                                                // Modify the JsonArray at the specified index
                                                val modifiedInnerArray = buildJsonArray {
                                                    innerArray.forEachIndexed { index, jsonElement ->
                                                        if (index == variable.transformIndexForList) {
                                                            add(JsonPrimitive(expressionResult))
                                                        } else {
                                                            add(jsonElement)
                                                        }
                                                    }
                                                }

                                                // Build the modified JsonElement from the innermost level to the outermost
                                                val modifiedV = v.toMutableList()
                                                    .apply { set(1, modifiedInnerArray) }
                                                    .toJsonArray()
                                                val modifiedK =
                                                    k.toMutableMap().apply { put("v", modifiedV) }
                                                        .toJsonObject()
                                                val modifiedKs =
                                                    ks.toMutableMap().apply { put("k", modifiedK) }
                                                        .toJsonObject()

                                                modifiedKs

                                                println("variable ${variable.key} updated with $modifiedKs")
                                                it.ks = modifiedKs
                                            }
                                        }
                                    }
                                }
                            }
                            else -> {}
                        }
                    }
                }
                else -> {}
            }
        }
        KPAnimationRuleTransformType.FRAME -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = variable.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        if (transformIndexForList < currentK.values.count()) {
                            list[transformIndexForList] = KPMultiDimensionalNodePrimitive(
                                value = JsonPrimitive(expressionResult)
                            )
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    is KPMultiDimensionalPrimitive -> {
                        KPMultiDimensionalPrimitive(
                            value = JsonPrimitive(expressionResult)
                        )
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK")
            layer.ks.p?.k = newK
        }
        KPAnimationRuleTransformType.FRAME_IT -> {
            val shape = layer.shapes?.firstOrNull { it.key == variable.transformKey } ?: return

            println("shape $shape")
            when (shape) {
                is KPShapeGroup -> {
                    shape.it.firstOrNull { it.key == variable.transformSubKey }?.let {
                        when (it) {
                            is KPShapeRect -> {
                                val newK: KPMultiDimensionalListOrPrimitive? =
                                    when (val currentK = it.s?.k) {
                                        is KPMultiDimensionalList -> {
                                            val transformIndexForList =
                                                variable.transformIndexForList ?: 0
                                            var list = currentK.values.toMutableList()
                                            if (transformIndexForList < currentK.values.count()) {
                                                list[transformIndexForList] =
                                                    KPMultiDimensionalNodePrimitive(
                                                        value = JsonPrimitive(expressionResult)
                                                    )
                                            }
                                            KPMultiDimensionalList(values = list)
                                        }
                                        is KPMultiDimensionalPrimitive -> {
                                            KPMultiDimensionalPrimitive(
                                                value = JsonPrimitive(expressionResult)
                                            )
                                        }
                                        else -> null
                                    }
                                println("variable ${variable.key} updated with $newK")
                                (it.s as? KPMultiDimensionalSimple)?.k = newK
                            }
                            else -> {}
                        }
                    }
                }
                else -> {}
            }
        }
        else -> {}

    }
}

fun MutableList<JsonElement>.toJsonArray(): JsonArray {
    return buildJsonArray {
        this@toJsonArray.forEach { jsonElement ->
            add(jsonElement)
        }
    }
}

fun MutableMap<String, JsonElement>.toJsonObject(): JsonObject {
    return buildJsonObject {
        this@toJsonObject.forEach { (key, jsonElement) ->
            put(key, jsonElement)
        }
    }
}