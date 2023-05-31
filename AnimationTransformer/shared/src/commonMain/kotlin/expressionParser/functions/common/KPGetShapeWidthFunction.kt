package expressionParser.functions.common

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList
import kotlinx.serialization.json.doubleOrNull
import lottieAnimation.KPLottieAnimation
import lottieAnimation.layer.KPShapeGroup
import lottieAnimation.layer.KPShapeLayer
import lottieAnimation.layer.KPShapeRect
import lottieAnimation.layer.properties.KPMultiDimensionalList
import lottieAnimation.layer.properties.KPMultiDimensionalNodePrimitive

class KPGetShapeWidthFunction(
    private val lottieAnimation: KPLottieAnimation,
) : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        if (argsDouble.count() != 1) return 0.0
        val ind = argsDouble[0].toInt()
        val layer = lottieAnimation.layers.find { it.ind == ind } ?: return 0.0
        if (layer !is KPShapeLayer) return 0.0
        val shape = layer.shapes?.firstOrNull() ?: return 0.0
        if (shape !is KPShapeGroup) return 0.0
        val it = shape.it.firstOrNull() ?: return 0.0
        if (it !is KPShapeRect) return 0.0
        val k = it.s?.k
        if (k !is KPMultiDimensionalList) return 0.0
        val values = k.values
        val result = when (val value1 = values.firstOrNull()) {
            is KPMultiDimensionalNodePrimitive -> {
                value1.value.doubleOrNull
            }
            else -> null
        } ?: return 0.0
        println("KPGetShapeWidthFunction = $result")
        return result
    }
}
