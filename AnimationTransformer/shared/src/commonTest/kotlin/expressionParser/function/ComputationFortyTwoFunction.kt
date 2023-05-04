package expressionParser.function

import expressionParser.FunctionInterface

class ComputationFortyTwoFunction : FunctionInterface {
    override fun execute(args: List<Double>): Double {
        return (args[0] + args[1]) * 100.0
    }
}