package expressionParser.functions.common

import expressionParser.KPFunctionInterface

class KPGetBottomOfSafeAreaFunction(
    private val projectHeight: Double,
    private val subtitleHeight: Double,
    private val watermarkHeight: Double
): KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val result = projectHeight - subtitleHeight - watermarkHeight
        println("KPGetBottomOfSafeAreaFunction = $result")
        return result
    }
}