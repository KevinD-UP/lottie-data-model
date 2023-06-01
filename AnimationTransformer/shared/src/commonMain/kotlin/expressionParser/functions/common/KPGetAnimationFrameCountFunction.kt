package expressionParser.functions.common

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import lottieAnimation.KPLottieAnimation

class KPGetAnimationFrameCountFunction(
    private val lottieAnimation: KPLottieAnimation,
) : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        if (argsDouble.isNotEmpty()) return 0.0
        val result = lottieAnimation.op.jsonPrimitive.doubleOrNull ?: return 0.0
        println("KPGetShapeWidthFunction = $result")
        return result
    }
}