package expressionParser.function

import expressionParser.KPFunctionInterface

class NoParamFunction : KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        return 123.0
    }
}