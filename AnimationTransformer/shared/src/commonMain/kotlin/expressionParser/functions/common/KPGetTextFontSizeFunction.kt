package expressionParser.functions.common

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPTextLayer

class KPGetTextFontSizeFunction(
    private val lottieAnimation: KPLottieAnimation,
): KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        if (argsDouble.count() != 1) return 0.0
        val ind = argsDouble[0].toInt()
        val layer = lottieAnimation.layers.find { it.ind == ind } ?: return 0.0
        if (layer !is KPTextLayer) return 0.0
        val item = layer.t.d.k.firstOrNull() ?: return 0.0
        val result = item.s.s?.jsonPrimitive?.doubleOrNull ?: return 0.0
        println("KPGetTextFontSizeFunction = $result")
        return result
    }

}
