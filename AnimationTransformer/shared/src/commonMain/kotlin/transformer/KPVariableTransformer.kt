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
                    animationResultWrapper.applyVariableUnitFunction(
                        variable = variable,
                        transformNode = transformNode,
                        expressionParser = expressionParser
                    )
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

fun KPLottieAnimationWrapper.applyVariableUnitFunction(
    variable: KPAnimationRuleVariable,
    transformNode: KPAnimationRuleVariableTransformNode,
    expressionParser: KPProjectExpressionParser
) {
    if (transformNode.transformType != KPAnimationRuleTransformType.UNIT) return
    val expressionResult =
        expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
    println("Expression: ${variable.value}")
    println("Expression result: $expressionResult")
}

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
    println("transformType: ${transformNode.transformType}")
    when (transformNode.transformType) {
        KPAnimationRuleTransformType.SOURCE_W -> {
            layer.sw = JsonPrimitive(expressionResult)
            println("->> variable ${variable.key} (transformType: ${transformNode.transformType})")
        }
        KPAnimationRuleTransformType.SOURCE_H -> {
            layer.sh = JsonPrimitive(expressionResult)
            println("->> variable ${variable.key} (transformType: ${transformNode.transformType})")
        }
        KPAnimationRuleTransformType.POSITION_X,
        KPAnimationRuleTransformType.POSITION_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_START_X,
        KPAnimationRuleTransformType.POSITION_START_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_START_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var sList = it.s?.toMutableList()
                                                if (sList != null && transformIndexForList < sList.count()) {
                                                    sList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.s = JsonArray(sList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_END_X,
        KPAnimationRuleTransformType.POSITION_END_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_END_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var eList = it.e?.toMutableList()
                                                if (eList != null && transformIndexForList < eList.count()) {
                                                    eList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.e = JsonArray(eList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} transformIndexForList: $transformIndexForList value: $expressionResult")
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
        KPAnimationRuleTransformType.ANCHOR_X,
        KPAnimationRuleTransformType.ANCHOR_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.a?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.ANCHOR_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
                                }
                            }
                            else -> {}
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.a as? KPMultiDimensionalSimple)?.k = newK
        }
        else -> {}
    }
    println("variable ${variable.key} updated with $expressionResult")
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
    println("transformType: ${transformNode.transformType}")
    when (transformNode.transformType) {
        KPAnimationRuleTransformType.POSITION_X,
        KPAnimationRuleTransformType.POSITION_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] = KPMultiDimensionalNodePrimitive(
                                        value = JsonPrimitive(expressionResult)
                                    )
                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_START_X,
        KPAnimationRuleTransformType.POSITION_START_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_START_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var sList = it.s?.toMutableList()
                                                if (sList != null && transformIndexForList < sList.count()) {
                                                    sList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.s = JsonArray(sList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_END_X,
        KPAnimationRuleTransformType.POSITION_END_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_END_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var eList = it.e?.toMutableList()
                                                if (eList != null && transformIndexForList < eList.count()) {
                                                    eList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.e = JsonArray(eList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_IT_X,
        KPAnimationRuleTransformType.POSITION_IT_Y -> {
            val shape = layer.shapes?.filterIndexed { index, _ -> index == transformNode.transformShapeIndex }?.firstOrNull()  ?: return
            when (shape) {
                is KPShapeGroup -> {
                    shape.it.filterIndexed { index, _ -> index == transformNode.transformShapeItIndex }?.firstOrNull()?.let {
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
                                                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_IT_Y) 1 else 0
                                                        if (index == transformIndexForList) {
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

                                                println("->> variable ${variable.key} updated with $modifiedKs")
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
        KPAnimationRuleTransformType.FRAME_WIDTH,
        KPAnimationRuleTransformType.FRAME_HEIGHT -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.FRAME_HEIGHT) 1 else 0
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
        KPAnimationRuleTransformType.FRAME_IT_WIDTH,
        KPAnimationRuleTransformType.FRAME_IT_HEIGHT -> {
            val shape = layer.shapes?.filterIndexed { index, _ -> index == transformNode.transformShapeIndex }?.firstOrNull()  ?: return
            println("shape $shape")
            when (shape) {
                is KPShapeGroup -> {
                    shape.it.filterIndexed { index, _ -> index == transformNode.transformShapeItIndex }?.firstOrNull()?.let {
                        when (it) {
                            is KPShapeRect -> {
                                val newK: KPMultiDimensionalListOrPrimitive? =
                                    when (val currentK = it.s?.k) {
                                        is KPMultiDimensionalList -> {
                                            val transformIndexForList =
                                                if (transformNode.transformType == KPAnimationRuleTransformType.FRAME_IT_HEIGHT) 1 else 0
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
    println("transformType: ${transformNode.transformType}")
    when (transformNode.transformType) {
        KPAnimationRuleTransformType.POSITION_X,
        KPAnimationRuleTransformType.POSITION_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_START_X,
        KPAnimationRuleTransformType.POSITION_START_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_START_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var sList = it.s?.toMutableList()
                                                if (sList != null && transformIndexForList < sList.count()) {
                                                    sList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.s = JsonArray(sList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.POSITION_END_X,
        KPAnimationRuleTransformType.POSITION_END_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.p?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.POSITION_END_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionNodeObject -> {
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                var eList = it.e?.toMutableList()
                                                if (eList != null && transformIndexForList < eList.count()) {
                                                    eList[transformIndexForList] =
                                                        JsonPrimitive(expressionResult)
                                                    it.e = JsonArray(eList)
                                                    println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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
        KPAnimationRuleTransformType.ANCHOR_X,
        KPAnimationRuleTransformType.ANCHOR_Y -> {
            val newK: KPMultiDimensionalListOrPrimitive? =
                when (val currentK = layer.ks.a?.k) {
                    is KPMultiDimensionalList -> {
                        val transformIndexForList = if (transformNode.transformType == KPAnimationRuleTransformType.ANCHOR_Y) 1 else 0
                        var list = currentK.values.toMutableList()
                        when (list.firstOrNull()) {
                            is KPMultiDimensionalNodePrimitive -> {
                                if (transformIndexForList < list.count()) {
                                    list[transformIndexForList] =
                                        KPMultiDimensionalNodePrimitive(
                                            value = JsonPrimitive(expressionResult)
                                        )
                                    println("variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
                                }
                            }
                            else -> {}
                        }
                        KPMultiDimensionalList(values = list)
                    }
                    else -> null
                }
            println("variable ${variable.key} updated with $newK (transformType: ${transformNode.transformType})")
            (layer.ks.a as? KPMultiDimensionalSimple)?.k = newK
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
                                list.forEachIndexed { index, it ->
                                    when (it) {
                                        is KPMultiDimensionNodeObject -> {
                                            if (transformNode.transformKeyframeIndexes?.contains(index) == true) {
                                                it.t = JsonPrimitive(expressionResult)
                                                println("->> variable ${variable.key} transformType: ${transformNode.transformType} value: $expressionResult")
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