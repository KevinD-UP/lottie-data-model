package expressionParser.function

import expressionParser.FunctionInterface

class NoParamFunction : FunctionInterface {
    override fun execute(args: List<Double>): Double {
        return 123.0
    }
}