package expressionParser.function

import expressionParser.KPFunctionInterface

class TextMeasureHeightFunction : KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        return 2.0 * args[0]
    }
}