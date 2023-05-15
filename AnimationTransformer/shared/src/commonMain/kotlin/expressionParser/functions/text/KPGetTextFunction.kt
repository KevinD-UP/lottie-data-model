package expressionParser.functions.text

import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPTextLayer

class KPGetTextFunction(
    private val lottieAnimation: KPLottieAnimation
    ) {
    fun execute(args: List<Double>): String {
        if (args.count() != 1) return ""
        val ind = args[0].toInt()
        val layer = lottieAnimation.layers.find { it.ind == ind } ?: return ""
        if (layer !is KPTextLayer) return ""
        val item = layer.t.d.k.firstOrNull() ?: return ""
        return item.s.t
    }
}