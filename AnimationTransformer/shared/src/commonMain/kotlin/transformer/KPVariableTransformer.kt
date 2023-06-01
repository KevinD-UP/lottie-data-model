package transformer

import expressionParser.KPProjectExpressionParser
import kotlinx.serialization.json.*
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.*
import lottieAnimation.layer.properties.*
import lottieAnimation.rules.properties.KPAnimationRuleTransformType
import lottieAnimation.rules.properties.KPAnimationRuleVariable
import lottieAnimation.rules.properties.KPAnimationRuleVariableTransformNode
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
            variable.transformNodes?.forEach { transformNode ->
                animation.layers.find { it.ind == transformNode.ind }?.let { layer ->
                    animationResultWrapper.applyVariableOnSolidLayer(
                        layer = layer,
                        variable = variable,
                        transformNode = transformNode,
                        expressionParser = expressionParser
                    )
                    animationResultWrapper.applyVariableOnTextLayer(
                        layer = layer,
                        variable = variable,
                        transformNode = transformNode,
                        expressionParser = expressionParser
                    )
                    animationResultWrapper.applyVariableOnShapeLayer(
                        layer = layer,
                        variable = variable,
                        transformNode = transformNode,
                        expressionParser = expressionParser
                    )
                }
            }

        }
        return animationResultWrapper.animation
    }
}

class KPLottieAnimationWrapper(var animation: KPLottieAnimation) {}

/**
 * Type layer 1
 */
fun KPLottieAnimationWrapper.applyVariableOnSolidLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    transformNode: KPAnimationRuleVariableTransformNode,
    expressionParser: KPProjectExpressionParser
) {
    // Return if the layer is not a KPTextLayer
    if (layer !is KPSolidLayer) return
    val expressionResult =
        expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
    println("Expression: ${variable.value}")
    println("Expression result: $expressionResult")

    when (transformNode.transformType) {
        KPAnimationRuleTransformType.SOURCE_W -> {
            layer.sw = JsonPrimitive(expressionResult)
            println("variable ${variable.key} (transformType: ${transformNode.transformType})")
        }
        KPAnimationRuleTransformType.SOURCE_H -> {
            layer.sh = JsonPrimitive(expressionResult)
            println("variable ${variable.key} (transformType: ${transformNode.transformType})")
        }
        else -> {}
    }
    println("variable ${variable.key} updated with $expressionResult")
}

/**
 * Type layer 5
 */
fun KPLottieAnimationWrapper.applyVariableOnTextLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    transformNode: KPAnimationRuleVariableTransformNode,
    expressionParser: KPProjectExpressionParser
) {
    // Return if the layer is not a KPTextLayer
    if (layer !is KPTextLayer) return
    val expressionResult =
        expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
    println("Expression: ${variable.value}")
    println("Expression result: $expressionResult")
    when (transformNode.transformType) {
        KPAnimationRuleTransformType.POSITION -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                }
                            }
                            else -> {}
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
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_S -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEach {
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (it.kpTransformKey == transformNode.transformKey) {
                                                var sList = it.s?.toMutableList()
                                                if (sList != null && transformIndexForList < sList.count()) {
                                                    sList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.s = JsonArray(sList)
                                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                                }
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                            }
                            else -> {}
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_E -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {

                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEach {
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (it.kpTransformKey == transformNode.transformKey) {
                                                var eList = it.e?.toMutableList()
                                                if (eList != null && transformIndexForList < eList.count()) {
                                                    eList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.e = JsonArray(eList)
                                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                                }
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                            }
                            else -> {}
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.ANCHOR -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.a?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                }
                            }
                            else -> {}
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.OUT_POINT -> {
            layer.op = JsonPrimitive(expressionResult)
            println("variable ${variable.key} updated with $expressionResult")
        }
        KPAnimationRuleTransformType.TIME -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEach {
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (it.kpTransformKey == transformNode.transformKey) {
                                                it.t = JsonPrimitive(expressionResult)
                                                println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                            }
                            else -> {}
                        }

                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        else -> {
            // Handle other transform types if necessary
        }
    }
}

/**
 * Type layer 4
 */
fun KPLottieAnimationWrapper.applyVariableOnShapeLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    transformNode: KPAnimationRuleVariableTransformNode,
    expressionParser: KPProjectExpressionParser
) {
    if (layer !is KPShapeLayer) {
        return
    }
    val expressionResult =
        expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
    println("Expression: ${variable.value}")
    println("Expression result: $expressionResult")

    when (transformNode.transformType) {
        KPAnimationRuleTransformType.POSITION -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] = KPMultiDimensionalNodePrimitive(
                                        value = JsonPrimitive(expressionResult)
                                    )
                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                }
                            }
                            else -> {}
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
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_S -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEach {
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (it.kpTransformKey == transformNode.transformKey) {
                                                var sList = it.s?.toMutableList()
                                                if (sList != null && transformIndexForList < sList.count()) {
                                                    sList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.s = JsonArray(sList)
                                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                                }
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                            }
                            else -> {}
                        }

                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_E -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEach {
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (it.kpTransformKey == transformNode.transformKey) {
                                                var eList = it.e?.toMutableList()
                                                if (eList != null && transformIndexForList < eList.count()) {
                                                    eList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.e = JsonArray(eList)
                                                    println("variable ${variable.key} transformType: ${transformNode.transformType} transformKey: ${transformNode.transformKey} value: $expressionResult")
                                                }
                                            }
                                        }
                                        else -> {}
                                    }
                                }
                            }
                            else -> {}
                        }

                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.p as? KPMultiDimensionalSimple)?.k = newK
        }
        KPAnimationRuleTransformType.POSITION_IT -> {
            val shape = layer.shapes?.firstOrNull { it.kpTransformKey == transformNode.transformKey } ?: return
            when (shape) {
                is KPShapeGroup -> {
                    println("variable.transformSubKey = ${transformNode.transformKey}")
                    println("shape it ${shape.it.firstOrNull { it.kpTransformKey == transformNode.transformKey }}")
                    shape.it.firstOrNull { it.kpTransformKey == transformNode.transformKey }?.let {
                        when (it) {
                            is KPShapeShape -> {
                                it.ks?.jsonObject?.let { ks ->
                                    ks["k"]?.jsonObject?.let { k ->
                                        k["v"]?.jsonArray?.let { v ->
                                            val index = transformNode.transformNodeVIndex ?: return
                                            v.getOrNull(index)?.jsonArray?.let { innerArray ->
                                                // Modify the JsonArray at the specified index
                                                val modifiedInnerArray = buildJsonArray {
                                                    innerArray.forEachIndexed { index, jsonElement ->
                                                        if (index == transformNode.transformIndexForList) {
                                                            add(JsonPrimitive(expressionResult))
                                                        } else {
                                                            add(jsonElement)
                                                        }
                                                    }
                                                }

                                                // Build the modified JsonElement from the innermost level to the outermost
                                                val modifiedV = v.toMutableList()
                                                    .apply { set(index, modifiedInnerArray) }
                                                    .toJsonArray()
                                                val modifiedK =
                                                    k.toMutableMap().apply { put("v", modifiedV) }
                                                        .toJsonObject()
                                                val modifiedKs =
                                                    ks.toMutableMap().apply { put("k", modifiedK) }
                                                        .toJsonObject()

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
                        val transformIndexForList = transformNode.transformIndexForList ?: 0
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
            val shape = layer.shapes?.firstOrNull { it.kpTransformKey == transformNode.transformKey } ?: return

            println("shape $shape")
            when (shape) {
                is KPShapeGroup -> {
                    shape.it.firstOrNull { it.kpTransformKey == transformNode.transformKey }?.let {
                        when (it) {
                            is KPShapeRect -> {
                                val newK: KPMultiDimensionalListOrPrimitive? =
                                    when (val currentK = it.s?.k) {
                                        is KPMultiDimensionalList -> {
                                            val transformIndexForList =
                                                transformNode.transformIndexForList ?: 0
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
        KPAnimationRuleTransformType.OUT_POINT -> {
            layer.op = JsonPrimitive(expressionResult)
            println("variable ${variable.key} updated with $expressionResult")
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