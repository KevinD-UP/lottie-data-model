package expressionParser.function

import expressionParser.FunctionInterface

class ComputationBlablaFunction : FunctionInterface {
    override fun execute(args: List<Double>): Double {
        return (args[0] + args[1] + args[2]) / 10.0
    }
}