package expressionParser.function

import expressionParser.KPFunctionInterface

class TestThirtyThreeFunction : KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        return args[0] + 33.0
    }
}