package expressionParser.functions

import expressionParser.KPFunctionInterface
import kotlin.math.max

class KPMaxFunction: KPFunctionInterface {
    override fun execute(args: List<Double>): Double {
        if (args.count() != 2) {
            return 0.0
        }
        return max(args[0], args[1])
    }
}