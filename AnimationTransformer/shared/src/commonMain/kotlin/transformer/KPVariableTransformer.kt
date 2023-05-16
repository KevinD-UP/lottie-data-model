package transformer

import expressionParser.KPProjectExpressionParser
import kotlinx.serialization.json.JsonPrimitive
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
            println("variable = ${variable.ind} - transformation = ${variable.transformType}")
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

class KPLottieAnimationWrapper(var animation: KPLottieAnimation) { }

fun KPLottieAnimationWrapper.applyVariableOnTextLayer(
    layer: KPLayer,
    variable: KPAnimationRuleVariable,
    expressionParser: KPProjectExpressionParser
) {
    // Return if the layer is not a KPTextLayer
    if (layer !is KPTextLayer) return

    // Get the mutable list of layers
    val layers: MutableList<KPLayer> = this.animation.layers.toMutableList()

    when (variable.transformType) {
        KPAnimationRuleTransformType.POSITION -> {
            val expressionResult = expressionParser.parseAndEvaluate(expression = variable.value, key = variable.key)
            println("Expression: ${variable.value}")
            println("Expression result: $expressionResult")
            val targetLayerIndex: Int = layers.indexOf(layer)
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
            val newP = (layer.ks.p as? KPMultiDimensionalSimple)?.copy(k = newK)
            val newTransform = layer.ks.copy(p = newP)
            val newLayer = layer.copy(ks = newTransform)
            layers[targetLayerIndex] = newLayer
            this.animation = this.animation.copy(layers = layers)
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
        println("is not shape layer")
        return
    }
}