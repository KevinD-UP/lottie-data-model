package expressionParser.function

import expressionParser.KPFunctionInterface

class ComputationFortyTwoFunction : KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        return (args[0] + args[1]) * 100.0
    }
}