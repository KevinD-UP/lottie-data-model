package expressionParser.function

import expressionParser.KPFunctionInterface

class NoParamFunction : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        return 123.0
    }
}