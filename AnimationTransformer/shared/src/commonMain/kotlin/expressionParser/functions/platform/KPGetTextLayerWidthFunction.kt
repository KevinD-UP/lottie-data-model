package expressionParser.functions.platform

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPTextLayer
import transformer.KPAnimationTransformerFunctionsDelegate

class KPGetTextLayerWidthFunction(
    private val lottieAnimation: KPLottieAnimation,
    private val functionsDelegate: KPAnimationTransformerFunctionsDelegate
): KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        if (argsDouble.count() != 1) return 0.0
        val ind = argsDouble[0].toInt()
        val layer = lottieAnimation.layers.find { it.ind == ind } ?: return 0.0
        if (layer !is KPTextLayer) return 0.0
        val item = layer.t.d.k.firstOrNull() ?: return 0.0
        val text = item.s.t
        val fontName = item.s.f
        val fontSize = item.s.s?.jsonPrimitive?.doubleOrNull ?: return 0.0
        val result = functionsDelegate.getTextLayerWidth(
            text = text,
            fontName = fontName,
            fontSize = fontSize
        )
        println("KPGetTextLayerWidthFunction = $result")
        return result
    }

}