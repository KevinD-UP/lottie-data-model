package expressionParser.function

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList

class ComputationBlablaFunction : KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        return (argsDouble[0] + argsDouble[1] + argsDouble[2]) / 10.0
    }
}