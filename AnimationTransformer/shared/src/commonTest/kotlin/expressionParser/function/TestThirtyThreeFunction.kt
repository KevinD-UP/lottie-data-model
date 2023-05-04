package expressionParser.function

import expressionParser.FunctionInterface

class TestThirtyThreeFunction : FunctionInterface {
    override fun execute(args: List<Double>): Double {
        return args[0] + 33.0
    }
}