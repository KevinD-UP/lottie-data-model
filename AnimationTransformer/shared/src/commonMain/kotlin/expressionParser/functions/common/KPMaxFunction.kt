package expressionParser.functions.common

import expressionParser.KPFunctionInterface
import expressionParser.extensions.toDoubleList
import kotlin.math.max

class KPMaxFunction: KPFunctionInterface {
    override fun execute(args: List<Any>): Double {
        val argsDouble = args.toDoubleList()
        if (argsDouble.count() != 2) {
            return 0.0
        }
        return max(argsDouble[0], argsDouble[1])
    }
}