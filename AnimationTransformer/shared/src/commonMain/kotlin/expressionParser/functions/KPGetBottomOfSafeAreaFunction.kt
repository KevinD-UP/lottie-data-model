package expressionParser.functions

import expressionParser.KPFunctionInterface

class KPGetBottomOfSafeAreaFunction(
    private val projectHeight: Double,
    private val subtitleHeight: Double,
    private val watermarkHeight: Double
): KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        return projectHeight - subtitleHeight - watermarkHeight
    }
}