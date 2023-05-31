package expressionParser

import expressionParser.functions.common.*
import expressionParser.functions.platform.KPGetAscentFunction
import expressionParser.functions.platform.KPGetLastLineBottomFunction
import expressionParser.functions.platform.KPGetTextLayerWidthFunction
import expressionParser.functions.platform.KPGetTextMeasureHeightFunction
import expressionParser.functions.store.KPStoreResultsFunction
import lottieAnimation.KPLottieAnimation
import transformer.KPAnimationTransformerFunctionsDelegate

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
        "getAscent" to KPGetAscentFunction(
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
        "getTextLayerWidth" to KPGetTextLayerWidthFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
        "getTextFontSize" to KPGetTextFontSizeFunction(
            lottieAnimation = animation
        ),
        "getShapeHeight" to KPGetShapeHeightFunction(
            lottieAnimation = animation
        ),
        "getShapeWidth" to KPGetShapeWidthFunction(
            lottieAnimation = animation
        ),
    )
    private val commonFunctions = mapOf(
        "min" to KPMinFunction(),
        "max" to KPMaxFunction(),
        "getResultForKey" to storeResults
    )
    private val functions = nativeFunctions + commonFunctions

    val expressionParser: KPExpressionParser = KPDefaultExpressionParser(functions = functions)

    fun parseAndEvaluate(expression: String, key: String? = null): Double {
        val result = expressionParser.parseAndEvaluate(expression)
        key?.let {
            storeResults.addResult(key, result)
        }
        return result
    }
}