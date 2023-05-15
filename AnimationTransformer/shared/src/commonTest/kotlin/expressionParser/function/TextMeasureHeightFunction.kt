package expressionParser.function

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList

class TextMeasureHeightFunction : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        return 2.0 * argsDouble[0]
    }
}