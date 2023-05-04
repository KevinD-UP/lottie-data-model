package expressionParser.function

import expressionParser.FunctionInterface

class TextMeasureHeightFunction : FunctionInterface {
    override fun execute(args: List<Double>): Double {
        return 2.0 * args[0]
    }
}