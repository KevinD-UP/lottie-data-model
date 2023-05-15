package expressionParser.function

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList

class TestThirtyThreeFunction : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        return argsDouble[0] + 33.0
    }
}