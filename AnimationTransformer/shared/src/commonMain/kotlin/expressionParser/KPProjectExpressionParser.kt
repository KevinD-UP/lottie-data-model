package expressionParser

import expressionParser.functions.*
import expressionParser.functions.common.KPMaxFunction
import expressionParser.functions.common.KPMinFunction
import expressionParser.functions.store.KPStoreResultsFunction
import lottieAnimation.KPLottieAnimation
import lottieAnimation.transformer.KPAnimationTransformerFunctionsDelegate

class KPProjectExpressionParser(
    animation: KPLottieAnimation,
    functionsDelegate: KPAnimationTransformerFunctionsDelegate,
    projectWidth: Double,
    projectHeight: Double,
    subtitleHeight: Double,
    watermarkHeight: Double,
) {
    private val storeResults = KPStoreResultsFunction()
    private val nativeFunctions = mapOf(
        "getAscent" to KPGetFirstLineTopFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
        "getLastLineDescent" to KPGetLastLineBottomFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
        "getBottomOfSafeArea" to KPGetBottomOfSafeAreaFunction(
            projectHeight = projectHeight,
            subtitleHeight = subtitleHeight,
            watermarkHeight = watermarkHeight
        ),
        "getTextMeasureHeight" to KPGetTextMeasureHeightFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
    )
    private val commonFunctions = mapOf(
        "min" to KPMinFunction(),
        "max" to KPMaxFunction(),
        "getResultForKey" to storeResults
    )
    private val functions =  nativeFunctions + commonFunctions

    val expressionParser: KPExpressionParser = KPDefaultExpressionParser(functions = functions)

    fun parseAndEvaluate(expression: String, key: String? = null): Double {
        val result = expressionParser.parseAndEvaluate(expression)
        key?.let {
            storeResults.addResult(key, result)
        }
        return result
    }
}