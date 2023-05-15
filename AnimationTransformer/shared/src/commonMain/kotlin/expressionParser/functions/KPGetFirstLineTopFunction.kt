package expressionParser.functions

import expressionParser.KPFunctionInterface
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPTextLayer
import lottieAnimation.transformer.KPAnimationTransformerFunctionsDelegate

class KPGetFirstLineTopFunction(
    private val lottieAnimation: KPLottieAnimation,
    private val functionsDelegate: KPAnimationTransformerFunctionsDelegate
): KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        if (args.count() != 1) return 0.0
        val ind = args[0].toInt()
        val layer = lottieAnimation.layers.find { it.ind == ind } ?: return 0.0
        if (layer !is KPTextLayer) return 0.0
        val item = layer.t.d.k.firstOrNull() ?: return 0.0
        val text = item.s.t
        val fontName = item.s.f
        val fontSize = item.s.s?.jsonPrimitive?.doubleOrNull ?: return 0.0
        return -1.0 * functionsDelegate.getFirstLineTop(
            text = text,
            fontName = fontName,
            fontSize = fontSize
        )
    }
}