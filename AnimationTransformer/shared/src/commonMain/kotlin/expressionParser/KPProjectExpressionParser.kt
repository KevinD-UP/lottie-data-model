package expressionParser

import expressionParser.functions.KPGetFirstLineTopFunction
import expressionParser.functions.KPGetBottomOfSafeAreaFunction
import expressionParser.functions.KPGetLastLineBottomFunction
import expressionParser.functions.KPGetTextMeasureHeightFunction
import lottieAnimation.KPLottieAnimation
import lottieAnimation.transformer.KPAnimationTransformerFunctionsDelegate

class KPProjectExpressionParser(
    animation: KPLottieAnimation,
    functionsDelegate: KPAnimationTransformerFunctionsDelegate,
    projectWidth: Double,
    projectHeight: Double,
    subtitleHeight: Double,
    watermarkHeight: Double,
): KPExpressionParser {
    private val functions = mapOf(
        "getAscent" to KPGetFirstLineTopFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
        "getLastLineDescent" to KPGetLastLineBottomFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
        "getBottomSafeArea" to KPGetBottomOfSafeAreaFunction(
            projectHeight = projectHeight,
            subtitleHeight = subtitleHeight,
            watermarkHeight = watermarkHeight
        ),
        "getTextMeasureHeight" to KPGetTextMeasureHeightFunction(
            lottieAnimation = animation,
            functionsDelegate = functionsDelegate
        ),
    )

    val expressionParser: KPExpressionParser = KPDefaultExpressionParser(functions = functions)

    override fun parseAndEvaluate(expression: String): Double {
        return expressionParser.parseAndEvaluate(expression)
    }
}